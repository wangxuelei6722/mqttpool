package com.wangxl.mqttpool.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ClassName: SimpleMqttClientProperties
 * @Description:
 * @Author
 * @Date 2022/3/16
 * @Version 1.0
 */
@Component
@Setter
@Getter
@ConfigurationProperties("mqtt.client")
public class SimpleMqttClientProperties {
    private String clientid;
    private String userName;
    private String password;
    private int timeOut;
    private int aliveTime;
    private int  maxConnectTimes;
    private String[] topics;
    private int[] qos;
}
