package com.wangxl.mqttpool.config;


import com.alibaba.fastjson.JSONObject;
import com.wangxl.mqttpool.mqtt.SimpleMqttClient;
import com.wangxl.mqttpool.mqtt.SimpleMqttPool;
import com.wangxl.mqttpool.pojo.NstrController;
import com.wangxl.mqttpool.service.NstrControllerService;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @ClassName: ScheduledTask
 * @Description: 定时任务，定时获取设备电流电压值等信息，存入数据库
 * @Author
 * @Date 2022/3/30
 * @Version 1.0
 */
@Component
@Slf4j
public class ScheduledOfTask {

    @Autowired
    private SimpleMqttPool mqttPool;

    @Autowired
    private NstrControllerService nstrControllerService;
    @Scheduled(cron = "0 */60 * * * ?")//每隔半小时执行一次
    public void execute(){
        log.info("ScheduledOfTask=========================");
        //查询控制器表
        List<NstrController> nstrControllerList = nstrControllerService.findNstrControllerList();
        MqttMessage mqttMessage = new MqttMessage();
        JSONObject jsonObject = new JSONObject();
        for (NstrController controller:nstrControllerList) {
            //如果IP 和controllerId有其中一个为空则执行发送消息。
             if(null!=controller.getIP()){
                 jsonObject.put("commandID", UUID.randomUUID().toString().replace("-",""));
                 jsonObject.put("IP",controller.getIP());
                 jsonObject.put("devId",controller.getController_id());
                 jsonObject.put("type","queryStatus");
                 jsonObject.put("dateTime",new Date());
                 mqttMessage.setQos(2);
                 String payload = jsonObject.toString();
                 mqttMessage.setPayload(payload.getBytes());
                 try {
                    SimpleMqttClient client = mqttPool.getClient();
                    client.publish("tyzw_scppro_queryStatus",mqttMessage);
                     mqttPool.releaseClient(client);
                 }catch (Exception e){
                     e.printStackTrace();
                 }
             }
        }
    }
}
