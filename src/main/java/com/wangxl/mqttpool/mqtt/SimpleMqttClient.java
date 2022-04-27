package com.wangxl.mqttpool.mqtt;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * @ClassName: SimpleMqttClient
 * @Description:
 * @Author
 * @Date 2022/3/16
 * @Version 1.0
 */
public class SimpleMqttClient  {
    /**
     * MQTT异步客户端
     */
    private MqttAsyncClient client = null;
    /**
     * 连接配置
     */
    private MqttConnectOptions options = null;
    /**
     * 客户端的ID，唯一且不可重复
     */
    private String clientid;
    private String userName;
    private String password;
    /**
     * 服务器地址
     */
    private String host;

    private int timeOut;
    private int aliveTime;
    /**
     * 订阅的主题列表
     */
    private String[] listTopic;
    /**
     * 主题列表对应的Qos列表
     */
    private int[] listQos;
    /**
     * 最大尝试连接次数
     */
    private int maxConnectTimes;
    /**
     * 客户端空闲标识
     */
    private boolean idled = true;


    public SimpleMqttClient(String clientid, String userName,String password, String host, int timeOut, int aliveTime, String[] listTopic, int[] listQos, int maxConnectTimes) {
        this.clientid = clientid;
        this.userName = userName;
        this.password = password;
        this.host = host;
        this.timeOut = timeOut;
        this.aliveTime = aliveTime;
        this.listTopic = listTopic;
        this.listQos = listQos;
        this.maxConnectTimes = maxConnectTimes;
    }

   

    /**
     * 连接MQTT服务器
     */
    public synchronized void   connect() {
        if (options == null) {
            setOptions();
        }
        if (client == null) {
            creatClient();
        }
        int connectTimes = 0;
        while (connectTimes < maxConnectTimes && !client.isConnected()) {
            try {
                IMqttToken token = client.connect(options);
                token.waitForCompletion();
                connectTimes++;
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(clientid + " 连接时发生错误： " + e.toString());
            }
        }
    }

    /**
     * 断开与MQTT服务器的连接
     */
    public synchronized void disconnect() {
        if (client != null && client.isConnected()) {
            try {
                IMqttToken token = client.disconnect();
                token.waitForCompletion();
            } catch (MqttException e) {
                e.printStackTrace();
                System.out.println(clientid + " 断开连接时发生错误： " + e.toString());
            }
        }
        client = null;
    }

    /**
     * 刷新MQTT的连接
     */
    public synchronized void refresh() {
        disconnect();
        setOptions();
        creatClient();
        connect();
    }

    /**
     * 共享消息订阅
     */
    public void sharedSubscribe() {
        if (client != null && client.isConnected()) {
            try {
                String[] topics = new String[listTopic.length];
                for (int i = 0; i < listTopic.length; i++) {
                    topics[i] = listTopic[i];
                }
                //EMQ共享订阅模式
                IMqttToken token = client.subscribe(topics, listQos);
                token.waitForCompletion();
            } catch (MqttException e) {
                e.printStackTrace();
                System.out.println(clientid + "订阅主题时发生错误: " + e.toString());
            }
        }
    }

    /**
     * 消息订阅
     */
    public void subscribe() {
        if (client != null && client.isConnected()) {
            try {
                IMqttToken token = client.subscribe(listTopic, listQos);
                token.waitForCompletion();
            } catch (MqttException e) {
                e.printStackTrace();
                System.out.println(clientid + "订阅主题时发生错误: " + e.toString());
            }
        }
    } /**
     * 消息订阅
     */
    public void subscribe(String topic,Integer qos) {
        if (client != null && client.isConnected()) {
            try {
                IMqttToken token = client.subscribe(topic, qos);
                token.waitForCompletion();
            } catch (MqttException e) {
                e.printStackTrace();
                System.out.println(clientid + "订阅主题时发生错误: " + e.toString());
            }
        }
    }

    /**
     * 消息推送
     *
     * @param topic   消息的主题名
     * @param message 消息报文
     */
    public void publish(String topic, MqttMessage message) {
        if (client != null && client.isConnected()) {
            try {
                IMqttDeliveryToken token = client.publish(topic, message);
                token.waitForCompletion();
            } catch (MqttException e) {
                e.printStackTrace();
                System.out.println(clientid + "推送消息时发生错误: " + e.toString());
            }
        }
    }

    /**
     * @return 是否处于连接状态
     */
    public boolean isConnected() {
        return client != null && client.isConnected();
    }

    public String getClientid() {
        return clientid;
    }

    public int getMaxConnectTimes() {
        return maxConnectTimes;
    }

    public boolean isIdled() {
        return idled;
    }

    public void setIdled(boolean idled) {
        this.idled = idled;
    }

    /**
     * 设置连接属性
     */
    private void setOptions() {
        if (options != null) {
            options = null;
        }
        options = new MqttConnectOptions();
        //将CleanSession设置为true时，一旦客户端断开连接，就会清除相关Session
        options.setCleanSession(true);
        options.setConnectionTimeout(timeOut);
        options.setKeepAliveInterval(aliveTime);
        options.setUserName(userName);
        options.setPassword(password.toCharArray());
        //org.eclipse.paho.client.mqttv3提供的自动重连，默认为false，也可以在回调中进行重连
        options.setAutomaticReconnect(true);
    }

    /**
     * 创建客户端
     */
    private void creatClient() {
        if (client == null) {
            try {
                // host为主机名，clientid即连接MQTT的客户端ID，一般以唯一标识符表示，MemoryPersistence设置clientid的保存形式，默认为以内存保存
                client = new MqttAsyncClient(host, clientid, new MemoryPersistence());
                // 设置回调函数
                client.setCallback(new SimpleMqttClientCallback(SimpleMqttClient.this));
                //setCallBack(new SimpleMqttClientCallback(SimpleMqttClient.this));
            } catch (MqttException e) {
                e.printStackTrace();
                System.out.println("创建连接客户端实例: [" + clientid + "] 时发生错误:" + e.toString());
            }
        }
    }
    public void  setCallBack(SimpleMqttClientCallback callBack){

        client.setCallback(callBack);
    }

}
