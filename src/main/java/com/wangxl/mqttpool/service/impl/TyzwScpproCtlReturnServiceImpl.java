package com.wangxl.mqttpool.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.wangxl.mqttpool.enums.ResultEnum;
import com.wangxl.mqttpool.pojo.TyzwScpproCtlReturn;
import com.wangxl.mqttpool.repository.TyzwScpproCtlReturnRepository;
import com.wangxl.mqttpool.service.TyzwScpproCtlReturnService;
import com.wangxl.mqttpool.utils.DateFormatUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @ClassName: TyzwScpproCtlReturnServiceImpl
 * @Description:
 * @Author
 * @Date 2022/3/24
 * @Version 1.0
 */
@Service
@Slf4j
public class TyzwScpproCtlReturnServiceImpl implements TyzwScpproCtlReturnService {
    @Autowired
    private TyzwScpproCtlReturnRepository tyzwScpproCtlReturnRepository;
    @Autowired
    private DateFormatUtil dateFormatUtil;
    @Override
    public String saveTyzwScpproCtlReturnService(String topic, String content) {

        log.info("===TyzwScpproCtlReturnServiceImpl====",topic);
        JSONObject jsonObject = JSONObject.parseObject(content);
        TyzwScpproCtlReturn tyzwScpproCtlReturn = new TyzwScpproCtlReturn();
        tyzwScpproCtlReturn.setCommandId(jsonObject.getString("commandID"));
        tyzwScpproCtlReturn.setDevId(jsonObject.getString("devId"));
        tyzwScpproCtlReturn.setIp(jsonObject.getString("IP"));
        tyzwScpproCtlReturn.setType(jsonObject.getString("type"));
        tyzwScpproCtlReturn.setDateTime(dateFormatUtil.strDateTime(jsonObject.getString("dateTime")));
        //String devstatus = jsonObject.getString("devstatus");
        //   JSONObject devstatu = JSONObject.parseObject(devstatus);
        //  tyzwScpproStatusReturn.setStatus(Integer.parseInt(devstatu.getString("status")));
        //  tyzwScpproStatusReturn.setCurrent(Integer.parseInt(devstatu.getString("current")));
        // tyzwScpproStatusReturn.setVoltage(Integer.parseInt(devstatu.getString("voltage")));
        // tyzwScpproStatusReturn.setPower(Integer.parseInt(devstatu.getString("power")));
        // tyzwScpproStatusReturn.setEnergy(Integer.parseInt(devstatu.getString("energy")));
        // tyzwScpproStatusReturn.setPowerFacto(Double.parseDouble(devstatu.getString("powerFacto")));
        tyzwScpproCtlReturn.setRetStatus(0);
        tyzwScpproCtlReturn.setStatus(Integer.parseInt(jsonObject.getString("status")));
        tyzwScpproCtlReturn.setCreateTime(new Date());

        TyzwScpproCtlReturn result = tyzwScpproCtlReturnRepository.save(tyzwScpproCtlReturn);
        if (result.getId()!=null){
            return ResultEnum.SUCESS.getMessage();
        }else {
            return ResultEnum.FAILED.getMessage();
        }
    }
}
