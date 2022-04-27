package com.wangxl.mqttpool.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.wangxl.mqttpool.enums.ResultEnum;
import com.wangxl.mqttpool.pojo.TyzwScpproStatusReturn;
import com.wangxl.mqttpool.repository.TyzwScpproStatusReturnRepository;
import com.wangxl.mqttpool.service.TyzwScpproStatusReturnService;
import com.wangxl.mqttpool.utils.DateFormatUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @ClassName: TyzwScpproStatusReturnServiceImpl
 * @Description:
 * @Author
 * @Date 2022/3/24
 * @Version 1.0
 */
@Service
@Slf4j
public class TyzwScpproStatusReturnServiceImpl implements TyzwScpproStatusReturnService {
    @Autowired
    private TyzwScpproStatusReturnRepository tyzwScpproStatusReturnRepository;
    @Autowired
    private DateFormatUtil dateFormatUtil;
    @Override
    public String saveTyzwScpproStatusReturnService(String topic, String content) {

        log.info("===TyzwScpproStatusReturnServiceImpl====",topic);
        JSONObject jsonObject = JSONObject.parseObject(content);
        TyzwScpproStatusReturn tyzwScpproStatusReturn = new TyzwScpproStatusReturn();
        tyzwScpproStatusReturn.setCommandId(jsonObject.getString("commandID"));
        tyzwScpproStatusReturn.setDevId(jsonObject.getString("devId"));
        tyzwScpproStatusReturn.setIp(jsonObject.getString("IP"));
        tyzwScpproStatusReturn.setType(jsonObject.getString("type"));
        String date = jsonObject.getString("dateTime");

         tyzwScpproStatusReturn.setDateTime(dateFormatUtil.strDateTime(jsonObject.getString("dateTime")));
        //String devstatus = jsonObject.getString("devstatus");
     //   JSONObject devstatu = JSONObject.parseObject(devstatus);
      //  tyzwScpproStatusReturn.setStatus(Integer.parseInt(devstatu.getString("status")));
      //  tyzwScpproStatusReturn.setCurrent(Integer.parseInt(devstatu.getString("current")));
       // tyzwScpproStatusReturn.setVoltage(Integer.parseInt(devstatu.getString("voltage")));
       // tyzwScpproStatusReturn.setPower(Integer.parseInt(devstatu.getString("power")));
       // tyzwScpproStatusReturn.setEnergy(Integer.parseInt(devstatu.getString("energy")));
       // tyzwScpproStatusReturn.setPowerFacto(Double.parseDouble(devstatu.getString("powerFacto")));
        tyzwScpproStatusReturn.setRetStatus(0);
        tyzwScpproStatusReturn.setCreateTime(new Date());

        TyzwScpproStatusReturn result = tyzwScpproStatusReturnRepository.save(tyzwScpproStatusReturn);
        if (result.getId()!=null){
            return ResultEnum.SUCESS.getMessage();
        }else {
            return ResultEnum.FAILED.getMessage();
        }

    }
}
