package com.wangxl.mqttpool.controller;

import com.wangxl.mqttpool.mqtt.SimpleMqttClient;
import com.wangxl.mqttpool.mqtt.SimpleMqttPool;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @ClassName: PublishController
 * @Description: 发布主题controller
 * @Author
 * @Date 2022/3/16
 * @Version 1.0
 */
@RestController
@RequestMapping("/publish")
public class PublishController {

    @Autowired
    private SimpleMqttPool mqttPool;


    @RequestMapping("/publishMessage")
    public void publish(String topic,Integer qos, String payload) throws Exception {
        MqttMessage mqttMessage = new MqttMessage();
        byte[] bytesPayload = payload.getBytes();
        mqttMessage.setQos(qos);
        mqttMessage.setPayload(bytesPayload);
        SimpleMqttClient client = mqttPool.getClient();

        try {
            client = mqttPool.getClient();
            client.publish(topic, mqttMessage);
            mqttPool.releaseClient(client);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   /* @RequestMapping("/status")
    public void sendQueryStatus(){
        *//**
         * tyzw_scppro_queryStatus
         * {
         *     "commandID": "15",
         *     "IP":"192.168.1.100",
         *     "devId":"11",
         *     "type":"queryStatus",
         *     "dateTime":"2021/04/01 14:14:14"
         * }
         *//*
        MqttMessage mqttMessage = new MqttMessage();
        JSONObject data = new JSONObject();
        data.put("commandID","147368488370781854766655820261275791105");
        data.put("IP","192.168.1.121");
        data.put("devId","YCSCPPROV1130202109290001");
        data.put("type","queryStatus");
        data.put("dateTime",new Date());
        mqttMessage.setQos(2);
        String payload = data.toString();
        mqttMessage.setPayload(payload.getBytes());
        try {

            SimpleMqttClient client = mqttPool.getClient();
            client.publish("tyzw_scppro_queryStatus",mqttMessage);
            mqttPool.releaseClient(client);
        }catch (Exception e){
            e.printStackTrace();
        }
    }*/
}
