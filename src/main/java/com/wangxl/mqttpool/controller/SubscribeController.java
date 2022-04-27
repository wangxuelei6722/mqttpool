package com.wangxl.mqttpool.controller;

import com.wangxl.mqttpool.mqtt.SimpleMqttClient;
import com.wangxl.mqttpool.mqtt.SimpleMqttPool;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @ClassName: SubscribeController
 * @Description: 订阅消息controller
 * @Author
 * @Date 2022/3/16
 * @Version 1.0
 */
@RestController
@RequestMapping("/subscribe")
public class SubscribeController {
    @Autowired
    private SimpleMqttPool mqttPool;

    @RequestMapping("/subscribeMessage")
    public void subscribe(String topic,Integer qos){

        try {
            SimpleMqttClient client = mqttPool.getClient();
            client.subscribe(topic,qos);
            mqttPool.releaseClient(client);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
