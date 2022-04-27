package com.wangxl.mqttpool.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ClassName: SimpleMqttPoolProperties
 * @Description:
 * @Author
 * @Date 2022/3/16
 * @Version 1.0
 */
@Component
@Setter
@Getter
@ConfigurationProperties("mqtt.pool")
public class SimpleMqttPoolProperties {
    private int  minPoolSize;
    private int maxPoolSize;
    private long overseeInterval;
}
