package com.wangxl.mqttpool.service.impl;

import com.wangxl.mqttpool.enums.ResultEnum;
import com.wangxl.mqttpool.service.*;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: NstrSubscriptionResultsServiceImpl
 * @Description: 处理返回结果数据，并存入数据库
 * @Author
 * @Date 2022/3/24
 * @Version 1.0
 */
@Service
@Slf4j
public class NstrSubscriptionResultsServiceImpl implements NstrSubscriptionResultsService {
    @Autowired
    private TyzwJsReturnService tyzwJsReturnService;
    @Autowired
    private TyzwScpproAddStudentsReturnService tyzwScpproAddStudentsReturnService;
    @Autowired
    private TyzwScpproAddTeacherReturnService tyzwScpproAddTeacherReturnService;
    @Autowired
    private TyzwScpproCardLogReturnService tyzwScpproCardLogReturnService;
    @Autowired
    private TyzwScpproCtlReturnService tyzwScpproCtlReturnService;
    @Autowired
    private TyzwScpproDevInfoReturnService tyzwScpproDevInfoReturnService;
    @Autowired
    private TyzwScpproEnergyClearZeroReturnService tyzwScpproEnergyClearZeroReturnService;
    @Autowired
    private TyzwScpproModifyNameReturnService tyzwScpproModifyNameReturnService;
    @Autowired
    private TyzwScpproStatusReturnService tyzwScpproStatusReturnService;
    @Autowired
    private TyzwScpproTimeInfoReturnService tyzwScpproTimeInfoReturnService;
    @Autowired
    private TyzwScpproQueryStatusReturnService tyzwScpproQueryStatusReturnService;



    @Override
    public String saveNstrSubscriptionResults(String topic, String content) {
        System.out.println("NstrSubscriptionResultsServiceImpl================saveNstrSubscriptionResults======="+topic);
        String result = ResultEnum.FAILED.getMessage();
        if (topic.equals("tyzw_scppro_cardLog")){
            //获取刷卡日志
            result = tyzwScpproCardLogReturnService.saveTyzwScpproCardLogReturn(topic,content);
        }else if (topic.equals("tyzw_scppro_queryStatus_return")){
            //服务器要状态，电压，电量等信息
            result = tyzwScpproQueryStatusReturnService.saveTyzwScpproQueryStatusReturnService(topic,content);

        }else if (topic.equals("tyzw_js_return")){
            //RTC校时
            result = tyzwJsReturnService.saveTyzwScpproQueryStatusReturnService(topic,content);

        }else if (topic.equals("tyzw_scppro_dev_info_return")){
            //设备信息获取
            result = tyzwScpproDevInfoReturnService.saveTyzwScpproDevInfoReturnService(topic,content);

        }else if (topic.equals("tyzw_scppro_time_info_return")){
            //服务器查询设备时间
            result = tyzwScpproTimeInfoReturnService.saveTyzwScpproTimeInfoReturnService(topic,content);

        }else if (topic.equals("tyzw_scppro_status_return")){
            //设备状态上传
            result = tyzwScpproStatusReturnService.saveTyzwScpproStatusReturnService(topic,content);

        }else if (topic.equals("tyzw_scppro_ctl_return")){
            //设备控制
            result = tyzwScpproCtlReturnService.saveTyzwScpproCtlReturnService(topic,content);

        }else if (topic.equals("tyzw_scppro_add_teacher_return")){
            //添加教师卡信息
            result = tyzwScpproAddTeacherReturnService.saveTyzwScpproAddTeacherReturnService(topic,content);

        }else if (topic.equals("tyzw_scppro_add_students_return")){
            //添加学生卡信息
            result = tyzwScpproAddStudentsReturnService.saveTyzwScpproAddStudentsReturnService(topic,content);

        }else if (topic.equals("tyzw_scppro_energyClearZero_return")){
            //电表清零功能
            result = tyzwScpproEnergyClearZeroReturnService.saveTyzwScpproEnergyClearZeroReturnService(topic,content);

        }else if (topic.equals("tyzw_scppro_modifyName_return")){
            //设备更名
            result = tyzwScpproModifyNameReturnService.saveTyzwScpproModifyNameReturnService(topic,content);

        }

        System.out.println("NstrSubscriptionResultsServiceImpl================saveNstrSubscriptionResults======="+result);

        return null;
    }
}
