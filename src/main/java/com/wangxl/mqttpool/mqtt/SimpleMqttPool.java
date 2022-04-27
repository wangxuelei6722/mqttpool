package com.wangxl.mqttpool.mqtt;

import com.wangxl.mqttpool.config.SimpleMqttClientProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName: SimpleMqttPool
 * @Description:
 * @Author
 * @Date 2022/3/16
 * @Version 1.0
 */
public class SimpleMqttPool {
    private static Logger logger = LoggerFactory.getLogger(SimpleMqttPool.class);
    /**
     * 用于存储所有的客户端
     * key： clientId
     * value: client
     */
    private Map<String, SimpleMqttClient> connectionMap = new ConcurrentHashMap<>();
    /**
     * 用于存储当前可用的空闲客户端
     */
    private List<SimpleMqttClient>  idleClients = new LinkedList<>();
    /**
     * 连接池最小连接数
     */
    private int minPoolSize;
    /**
     * 连接池最大连接数
     */
    private int maxPoolSize;
    /**
     * 监视线程的间隔实际
     */
    private long overseeInterval;
    /**
     * 连接服务器地址
     */
    private String host;
    /**
     * 连接的配置信息
     */
    private SimpleMqttClientProperties clientProperties;
    /**
     * 用于存储客户端处于连接中断状态下被监视线程扫描的次数
     * key: clientId
     * value: times
     */
    private Map<String, Integer> disconnectedClientMap = new HashMap<>();
    /**
     * 用于存储客户端处于忙碌状态下被监视线程扫描的次数
     * key: clientId
     * value: times
     */
    private Map<String, Integer> busyClientMap = new HashMap<>();
    /**
     * 超时回数
     * 当监视线程检测到一个客户端处于忙碌的状态的回数超过该值时，更改客户端的状态
     */
    private final int OVER_TIMES = 5;

    public SimpleMqttPool(int minPoolSize, int maxPoolSize, long overseeInterval, String host, SimpleMqttClientProperties clientProperties) {
        this.minPoolSize = minPoolSize;
        this.maxPoolSize = maxPoolSize;
        this.overseeInterval = overseeInterval;
        this.host = host;
        this.clientProperties = clientProperties;
    }

    /**
     * 连接池初始化
     */
    public void initialize() {
        String clientIdProperty = clientProperties.getClientid();
        for (int i = 0; i < minPoolSize; i++) {
            //应为MQTT连接的客户端要求clientId不能重复，在这里做些处理
            String clientId = clientIdProperty + "_" + (i + 1);
            SimpleMqttClient mqttClient = new SimpleMqttClient(clientId,
                    clientProperties.getUserName(),
                    clientProperties.getPassword(),
                    host,
                    clientProperties.getTimeOut(),
                    clientProperties.getAliveTime(),
                    clientProperties.getTopics(),
                    clientProperties.getQos(),
                    clientProperties.getMaxConnectTimes());
            //连接客户端
            mqttClient.connect();
            //将创建的客户端保存在集合中
            connectionMap.put(clientId, mqttClient);
            idleClients.add(mqttClient);
        }

        //启动监视线程
        Thread thread = new Thread(new Watcher());
        thread.start();
    }

    /**
     * 获取连接
     *
     * @return
     * @throws Exception
     */
    public synchronized SimpleMqttClient getClient() throws Exception {
        SimpleMqttClient client = null;
        try {
            synchronized (idleClients) {
                //创建一个迭代器，用于获取连接
                Iterator<SimpleMqttClient> iterator = idleClients.iterator();
                while (iterator.hasNext()) {
                    SimpleMqttClient simpleMqttClient = iterator.next();
                    // 判断连接是否可用
                    if (isAvailableClient(simpleMqttClient)) {
                        client = simpleMqttClient;
                        logger.info("连接被取出,ID为: " + client.getClientid());
                        //从空闲池中删除该连接
                        iterator.remove();
                        break;
                    }
                }
            }

            //如果此时client还为空，说明当前空闲池中没有可用的连接，创建新的连接
            if (client == null) {
                client = createNewClient();
            }
            //将连接对象设为非空闲状态
            client.setIdled(false);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return client;
    }

    /**
     * 释放连接
     *
     * @param client
     */
    public void releaseClient(SimpleMqttClient client) {
        synchronized (idleClients) {
            idleClients.add(client);
            logger.info("将ID为 【" + client.getClientid() + "】 的连接放回连接池中");
        }
    }

    /**
     * 判断连接对象是否可用
     *
     * @param client
     * @return
     */
    private boolean isAvailableClient(SimpleMqttClient client) {
        return client.isConnected() && client.isIdled();
    }

    /**
     * 创建未被使用clientId
     *
     * @return clientId
     */
    private String createClientId() {
        int i = 1;
        String clientId = clientProperties.getClientid() + "_" + i;
        while (connectionMap.get(clientId) != null) {
            i++;
            clientId = clientProperties.getClientid() + "_" + i;
        }
        return clientId;
    }

    /**
     * 创建一个新的客户端并连接
     *
     * @return 连接客户端
     * @throws Exception 已达连接池上线
     */
    private SimpleMqttClient createNewClient() throws Exception {
        SimpleMqttClient client = null;
        if (connectionMap.size() < maxPoolSize) {
            //获取未被使用客户端ID
            String clientId = createClientId();
            //创建新的连接
            SimpleMqttClient mqttClient = new SimpleMqttClient(clientId,
                    clientProperties.getUserName(),
                    clientProperties.getPassword(),
                    host,
                    clientProperties.getTimeOut(),
                    clientProperties.getAliveTime(),
                    clientProperties.getTopics(),
                    clientProperties.getQos(),
                    clientProperties.getMaxConnectTimes());
            mqttClient.connect();
            logger.info("新建连接对象, ID为：" + clientId);
            client = mqttClient;
            //把连接保存在集合中
            connectionMap.put(clientId, mqttClient);
        } else {
            throw new Exception("MQTT连接池连接数已达最大值");
        }
        return client;
    }

    class Watcher implements Runnable {
        @Override
        public void run() {
            while (true) {
                logger.info("__________监视线程执行中___________");
                try {
                    synchronized (idleClients) {
                        logger.info("当前连接池连接总数： " + connectionMap.size());
                        connectionMap.values().forEach(client -> logger.info(client != null ? client.getClientid() : null));
                        logger.info("当前连接池可用连接个数： " + idleClients.size());
                        idleClients.forEach(client -> logger.info(client != null ? client.getClientid() : null));
                        //1.清除无效连接
                        clearUnusableConnections();
                        //2.修改处于忙碌的连接状态
                        refreshBusyConnections();
                        //3.清除多余连接
                        closeConnections();
                        //4.如果连接数量小于最小连接数量，创建连接
                        createClients();
                    }
                    logger.info("__________监视线程执行完毕___________");
                    Thread.sleep(overseeInterval);
                } catch (Exception e) {
                    logger.error("监视线程执行时发生错误：" + e.toString());
                }
            }
        }

        private boolean isBusy() {
            //空闲池中可用的连接数大于最小连接数时为非繁忙状态
            int availableClientNum = (int) idleClients.stream().filter(SimpleMqttPool.this::isAvailableClient).count();
            logger.info("连接池当前可用连接数:" + availableClientNum);
            return availableClientNum < minPoolSize;
        }

        /**
         * 清楚不可用的连接，连接中断的，虽然客户端设置了断线重连策略，但也有可能连不上服务器，这些连接需要被清除
         * 设置检查到一个客户端连接中断被检查到一定次数后，将其清除
         */
        private void clearUnusableConnections() {
            logger.info("正在清除无效连接");
            try {
                Collection<SimpleMqttClient> connections = connectionMap.values();
                Iterator<SimpleMqttClient> iterator = connections.iterator();
                while (iterator.hasNext()) {
                    SimpleMqttClient client = iterator.next();
                    if (!client.isConnected()) {
                        Integer times = disconnectedClientMap.containsKey(client.getClientid()) ? disconnectedClientMap.get(client.getClientid()) : 0;
                        times++;
                        if (times > OVER_TIMES) {
                            connectionMap.remove(client.getClientid());
                            SimpleMqttClient finalClient = client;
                            idleClients.removeIf((idleClient) -> idleClient.getClientid() == finalClient.getClientid() || idleClient == null);
                            disconnectedClientMap.remove(client.getClientid());
                            logger.info("无效连接被清除,ID为 " + client.getClientid());
                            client.disconnect();
                            client = null;
                        } else {
                            disconnectedClientMap.put(client.getClientid(), times);
                        }
                    }
                }
            } catch (Exception e) {
                logger.error("清除无效连接时发生异常： " + e.toString());
                e.printStackTrace();
            }

        }

        /**
         * 通过回调设置连接客户端的空闲标识，如果回调失败，则客户端永远处于忙碌状态，需要通过监视线程修改这些客户端的标识
         * 这里设置监视线程监视到一个客户端处于忙碌状态达到一定次数后，强制更改其状态
         */
        private void refreshBusyConnections() {
            logger.info("正在重置忙碌连接");
            try {
                Collection<SimpleMqttClient> connections = connectionMap.values();
                Iterator<SimpleMqttClient> iterator = connections.iterator();
                while (iterator.hasNext()) {
                    SimpleMqttClient client = iterator.next();
                    if (isAvailableClient(client)) {
                        busyClientMap.put(client.getClientid(), 0);
                    } else {
                        Integer times = busyClientMap.containsKey(client.getClientid()) ? busyClientMap.get(client.getClientid()) : 0;
                        times++;
                        if (times > OVER_TIMES) {
                            if (client.isConnected()) {
                                //将标识位改为空闲
                                client.setIdled(true);
                                logger.info("忙碌连接被重置,ID为 " + client.getClientid());
                                times = 0;
                            }
                            busyClientMap.put(client.getClientid(), times);
                        }
                    }
                }
            } catch (Exception e) {
                logger.error("重置忙碌连接时发生异常: " + e.toString());
                e.printStackTrace();
            }

        }

        /**
         * 关闭多余连接
         */
        private void closeConnections() {
            if (!isBusy()) {
                logger.info("正在关闭多余连接");
                try {
                    Iterator<SimpleMqttClient> iterator = idleClients.iterator();
                    while (iterator.hasNext() && idleClients.size() > minPoolSize) {
                        SimpleMqttClient client = iterator.next();
                        if (isAvailableClient(client)) ;
                        iterator.remove();
                        connectionMap.remove(client.getClientid());
                        busyClientMap.remove(client.getClientid());
                        client.disconnect();
                        logger.info("多余连接被清除,ID为 " + client.getClientid());
                        client = null;
                    }
                } catch (Exception e) {
                    logger.error("关闭多余连接时发生异常: " + e.toString());
                    e.printStackTrace();
                }
            }
        }

        /**
         * 监测连接池中连接个数，如果连接小于最小连接个数，创建连接
         */
        private void createClients() {
            if (connectionMap.size() < minPoolSize) {
                logger.info("正在创建新的连接");
                for (int i = connectionMap.size(); i < minPoolSize; i++) {
                    try {
                        SimpleMqttClient client = createNewClient();
                        client.setIdled(true);
                        idleClients.add(client);
                    } catch (Exception e) {
                        logger.error("监视线程创建新的连接时发生异常：" + e.toString());
                        e.printStackTrace();
                    }

                }
            }
        }
    }
}
