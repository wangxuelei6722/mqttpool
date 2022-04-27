package com.wangxl.mqttpool.service;

import org.eclipse.paho.client.mqttv3.MqttMessage;

public interface TyzwScpproCardLogReturnService {

    String saveTyzwScpproCardLogReturn(String topic, String content);
}
