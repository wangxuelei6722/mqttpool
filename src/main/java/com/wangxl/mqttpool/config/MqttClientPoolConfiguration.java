package com.wangxl.mqttpool.config;

import com.wangxl.mqttpool.mqtt.SimpleMqttPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: MqttClientPoolConfiguration
 * @Description:
 * @Author
 * @Date 2022/3/16
 * @Version 1.0
 */
@Configuration
public class MqttClientPoolConfiguration {
    @Value("${mqtt.host}")
    private String host;
    @Autowired
    private SimpleMqttClientProperties simpleMqttClientProperties;
    @Autowired
    private SimpleMqttPoolProperties simpleMqttPoolProperties;

    @Bean("mqttPool")
    public SimpleMqttPool mqttPool() {
        SimpleMqttPool simpleMqttPool = new SimpleMqttPool(simpleMqttPoolProperties.getMinPoolSize(),
                simpleMqttPoolProperties.getMaxPoolSize(),
                simpleMqttPoolProperties.getOverseeInterval(),
                host,
                simpleMqttClientProperties);
        return simpleMqttPool;
    }

}
