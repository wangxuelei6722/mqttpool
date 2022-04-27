package com.wangxl.mqttpool.mqtt;

import com.alibaba.fastjson.JSONObject;
import com.wangxl.mqttpool.pojo.TyzwScpproCardLogReturn;
import com.wangxl.mqttpool.service.*;
import com.wangxl.mqttpool.utils.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName: SimpleMqttClientCallback
 * @Description:
 * @Author
 * @Date 2022/3/16
 * @Version 1.0
 */
@Slf4j
public class  SimpleMqttClientCallback implements MqttCallbackExtended {

    private SimpleMqttClient client;
    // 获取bean上下文
    ApplicationContext context = SpringUtil.context;
    NstrSubscriptionResultsService nstrSubscriptionResultsService = context
            .getBean(NstrSubscriptionResultsService.class);

    public SimpleMqttClientCallback(SimpleMqttClient client) {
        this.client = client;
   }

    @Override
    public void connectComplete(boolean b, String s) {
        log.info("SimpleMqttClientCallback-------connectComplete()---------",client.getClientid());
        //连接成功后，自动订阅主题
       client.sharedSubscribe();
    }

    @Override
    public void connectionLost(Throwable throwable) {
        log.info("SimpleMqttClientCallback-------connectionLost()---------",client.getClientid());
        //可以在此处做重连处理
       //0  client.refresh();
        client.disconnect();
    }

    @Override
    public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
        String result="";
        LocalDateTime startTime = LocalDateTime.now();
        log.info("SimpleMqttClientCallback----------messageArrived()-------",client.getClientid()+"----成功接收消息！---- 时间： "
                +startTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")));
        String content = new String(mqttMessage.getPayload(), StandardCharsets.UTF_8);
        log.info("SimpleMqttClientCallback----------接收主题-------",topic,"接收消息Qos : ",mqttMessage.getQos(),"接收消息内容 : ",content);
        if (client.getClientid().equals("share_1"))
            result =  nstrSubscriptionResultsService.saveNstrSubscriptionResults(topic,content);

        log.info("SimpleMqttClientCallback----------messageArrived()-------消息入库",result);
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        log.info("SimpleMqttClientCallback--------deliveryComplete()------"+client.getClientid()+"----成功发送消息！---- 时间： "
        + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")));
        //消息发送成功后，将客户端设为空闲状态
        client.setIdled(true);
    }
    public  String unicodeToString(String str) {

        Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
        Matcher matcher = pattern.matcher(str);
        char ch;
        while (matcher.find()) {
            ch = (char) Integer.parseInt(matcher.group(2), 16);
            str = str.replace(matcher.group(1), ch + "");
        }
        return str;
    }
}
