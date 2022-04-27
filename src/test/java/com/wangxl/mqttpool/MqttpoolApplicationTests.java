package com.wangxl.mqttpool;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wangxl.mqttpool.mqtt.SimpleMqttClient;
import com.wangxl.mqttpool.mqtt.SimpleMqttPool;
import com.wangxl.mqttpool.utils.UUIDutil;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;

@SpringBootTest
class MqttpoolApplicationTests {
    @Autowired
    private SimpleMqttPool mqttPool;

    private UUIDutil uuiDutil;


    @Test
    void contextLoads() {
    }
    @Test
    public void testTyzwScpproQueryStatusReturn(){
        /**
         * tyzw_scppro_queryStatus
         * {
         *     "commandID": "15",
         *     "IP":"192.168.1.100",
         *     "devId":"11",
         *     "type":"queryStatus",
         *     "dateTime":"2021/04/01 14:14:14"
         * }
         */
        MqttMessage mqttMessage = new MqttMessage();
        JSONObject data = new JSONObject();
        data.put("commandID","147368488370781854766655820261275791105");
        data.put("IP","192.168.1.121");
        data.put("devId","YCSCPPROV1130202109290001");
        data.put("type","student");
        data.put("devCOUNT","1");
        data.put("dateTime",new Date());
        JSONArray array = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("className", "机时预约");
        jsonObject.put("classRoom", "2003");
        jsonObject.put("course", "化学");
        jsonObject.put("teacher", "管理员");
        jsonObject.put("studentName", "张敏2");
        jsonObject.put("sex", "女");
        jsonObject.put("studentNumber", "11021");
        jsonObject.put("rfidNumber", "658523304");
        jsonObject.put("devInfo" , "测试");
        jsonObject.put("position" , "power1-4");
        jsonObject.put("AppointmentDate", "2022-04-06");
        jsonObject.put("AppointmentTime", "17:00:00--18:20:00");
        array.add(jsonObject);
        data.put("studentCards",array);
        mqttMessage.setQos(2);
        mqttMessage.setPayload(data.toJSONString().getBytes(StandardCharsets.UTF_8));
        try {

        SimpleMqttClient client = mqttPool.getClient();
        client.publish("tyzw_scppro_add_students",mqttMessage);
        mqttPool.releaseClient(client);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Test
    public void getUUID(){
       String uuidString = UUID.randomUUID().toString().replace("-","");
        long longString = System.currentTimeMillis();



        System.out.println("UUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU"+uuidString);
    }

    @Test
    public void retDate(){

       /* tyzw_scppro_add_student
        {
            "commandID":"147368488370781854766655820261275791105",
            "IP":"192.168.1.121",
            "devId":"YCSCPPROV1130202109290001",
            "type":"student",
            "devCOUNT":"1",
            "dateTime":"2022-04-06 17:20:00",
            "studentCards":[{
                "className", "机时预约",
                "classRoom", "2003",
                "course", "化学",
                "teacher", "管理员",
                "studentName", "张敏2",
                "sex", "女",
                "studentNumber", "11021",
                "rfidNumber", "658523304",
                "devInfo" , "测试",
                "position" , "power1-4",
                "AppointmentDate", "2022-04-06",
                "AppointmentTime", "17:00:00--18:20:00"
        }]
        }*/

        MqttMessage mqttMessage = new MqttMessage();
        JSONObject data = new JSONObject();
        data.put("commandID",new Date());
        mqttMessage.setQos(2);
        mqttMessage.setPayload(data.toJSONString().getBytes(StandardCharsets.UTF_8));
        try {

            SimpleMqttClient client = mqttPool.getClient();
            client.publish("tyzw_js",mqttMessage);
            mqttPool.releaseClient(client);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("DDDDDDDDDDDDDDDDDDDDDDDDDDDDD=============DDDDDD==="+new Date());
    }



}
