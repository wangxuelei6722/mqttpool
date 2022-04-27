package com.wangxl.mqttpool.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.wangxl.mqttpool.enums.ResultEnum;
import com.wangxl.mqttpool.pojo.TyzwScpproCardLogReturn;
import com.wangxl.mqttpool.repository.TyzwScpproCardLogReturnRepository;
import com.wangxl.mqttpool.service.TyzwScpproCardLogReturnService;
import com.wangxl.mqttpool.utils.DateFormatUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @ClassName: TyzwScpproCardLogReturnServiceImpl
 * @Description: 获取刷卡日志，并将订阅消息存入数据库
 * @Author
 * @Date 2022/3/24
 * @Version 1.0
 */
@Service
@Slf4j
public class TyzwScpproCardLogReturnServiceImpl implements TyzwScpproCardLogReturnService {

    @Autowired
    private TyzwScpproCardLogReturnRepository tyzwScpproCardLogReturnRepository;
    @Autowired
    private DateFormatUtil dateFormatUtil;
    @Override
    public String saveTyzwScpproCardLogReturn(String topic, String content) {
        System.out.println("========================="+ topic);
        JSONObject jsonObject = JSONObject.parseObject(content);
        TyzwScpproCardLogReturn tyzwScpproCardLogReturn = new TyzwScpproCardLogReturn();
        tyzwScpproCardLogReturn.setCommandId(jsonObject.getString("commandID"));
        tyzwScpproCardLogReturn.setDevId(jsonObject.getString("devId"));
        tyzwScpproCardLogReturn.setIp(jsonObject.getString("IP"));
        tyzwScpproCardLogReturn.setType(jsonObject.getString("type"));
        tyzwScpproCardLogReturn.setDateTime(dateFormatUtil.strDateTime(jsonObject.getString("dateTime")));
        String cardLogs = jsonObject.getString("cardLog");
        JSONObject cardLog = JSONObject.parseObject(cardLogs);
        tyzwScpproCardLogReturn.setRfidNumber(cardLog.getInteger("rfidNumber"));
        tyzwScpproCardLogReturn.setCarType(cardLog.getString("type"));
        tyzwScpproCardLogReturn.setDevInfo(cardLog.getString("state"));
        tyzwScpproCardLogReturn.setDevInfo(cardLog.getString("devInfo"));
        tyzwScpproCardLogReturn.setPayCardTime(dateFormatUtil.strDateTime(jsonObject.getString("dateTime")));
        tyzwScpproCardLogReturn.setDevstatus(jsonObject.getString("devstatus"));
        tyzwScpproCardLogReturn.setCreateTime(new Date());

        TyzwScpproCardLogReturn  result =  tyzwScpproCardLogReturnRepository.save(tyzwScpproCardLogReturn);

        if (result.getId()!=null){
            return ResultEnum.SUCESS.getMessage();
        }else {
            return ResultEnum.FAILED.getMessage();
        }
    }
}
