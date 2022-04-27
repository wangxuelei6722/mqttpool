package com.wangxl.mqttpool.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.wangxl.mqttpool.enums.ResultEnum;
import com.wangxl.mqttpool.pojo.TyzwScpproQueryStatusReturn;
import com.wangxl.mqttpool.repository.TyzwScpproQueryStatusReturnRepository;
import com.wangxl.mqttpool.service.TyzwScpproQueryStatusReturnService;
import com.wangxl.mqttpool.utils.DateFormatUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @ClassName: TyzwScpproQueryStatusReturnServiceImpl
 * @Description:
 * @Author
 * @Date 2022/3/24
 * @Version 1.0
 */
@Service
@Slf4j
public class TyzwScpproQueryStatusReturnServiceImpl implements TyzwScpproQueryStatusReturnService {

    @Autowired
    private TyzwScpproQueryStatusReturnRepository tyzwScpproQueryStatusReturnRepository;
    @Autowired
    private DateFormatUtil dateFormatUtil;
    @Override
    public String saveTyzwScpproQueryStatusReturnService(String topic, String content) {
        TyzwScpproQueryStatusReturn result = null;
        content = content.replace("retStatus ","retStatus");
        JSONObject jsonObject = JSONObject.parseObject(content);
        TyzwScpproQueryStatusReturn tyzwScpproQueryStatusReturn = new TyzwScpproQueryStatusReturn();
        tyzwScpproQueryStatusReturn.setCommandId(jsonObject.getString("commandID"));
        tyzwScpproQueryStatusReturn.setIp(jsonObject.getString("IP"));
        tyzwScpproQueryStatusReturn.setDevId(jsonObject.getString("devId"));
        tyzwScpproQueryStatusReturn.setType(jsonObject.getString("type"));
        tyzwScpproQueryStatusReturn.setDateTime(dateFormatUtil.strDateTime(jsonObject.getString("dateTime")));
        try{
            String retStatus = jsonObject.getString("retStatus"+"\" ");
        tyzwScpproQueryStatusReturn.setRetStatus(Integer.parseInt(jsonObject.getString("retStatus")));

        }catch (Exception e){
            e.printStackTrace();
        }
        String devstatuString = jsonObject.getString("devstatus");
        JSONObject devstatus = JSONObject.parseObject(devstatuString);
        tyzwScpproQueryStatusReturn.setIsOnline(Integer.parseInt(devstatus.getString("isOnline")));
        tyzwScpproQueryStatusReturn.setStatus(Integer.parseInt(devstatus.getString("status")));
        tyzwScpproQueryStatusReturn.setCurrent(Integer.parseInt(devstatus.getString("current")));
        tyzwScpproQueryStatusReturn.setVoltage(Integer.parseInt(devstatus.getString("voltage")));
        tyzwScpproQueryStatusReturn.setPower(Integer.parseInt(devstatus.getString("power")));
        tyzwScpproQueryStatusReturn.setEnergy(Integer.parseInt(devstatus.getString("energy")));
        tyzwScpproQueryStatusReturn.setPowerFacto(Double.parseDouble(devstatus.getString("powerFacto")));
        tyzwScpproQueryStatusReturn.setCreateTime(new Date());
        //判断
        result = tyzwScpproQueryStatusReturnRepository.save(tyzwScpproQueryStatusReturn);
        if (result.getId()!=null){
            return ResultEnum.SUCESS.getMessage();
        }else {
            return ResultEnum.FAILED.getMessage();
        }
    }
}
