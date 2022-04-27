package com.wangxl.mqttpool.initialize;

import com.wangxl.mqttpool.mqtt.SimpleMqttPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @ClassName: Initialize
 * @Description:
 * @Author
 * @Date 2022/3/16
 * @Version 1.0
 */
@Component
public class Initialize implements CommandLineRunner {
    @Autowired
    private SimpleMqttPool mqttPool;

    @Override
    public void run(String... args) throws Exception {
        mqttPool.initialize();
    }
}
