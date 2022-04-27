package com.wangxl.mqttpool.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.wangxl.mqttpool.enums.ResultEnum;
import com.wangxl.mqttpool.pojo.TyzwJsReturn;
import com.wangxl.mqttpool.repository.TyzwJsReturnRepository;
import com.wangxl.mqttpool.service.TyzwJsReturnService;
import com.wangxl.mqttpool.utils.DateFormatUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: TyzwJsReturnServiceImpl
 * @Description: RTC校时
 * @Author
 * @Date 2022/3/24
 * @Version 1.0
 */
@Service
@Slf4j
public class TyzwJsReturnServiceImpl implements TyzwJsReturnService {

    @Autowired
    private TyzwJsReturnRepository tyzwJsReturnRepository;
    @Autowired
    private DateFormatUtil dateFormatUtil;
    @Override
    public String saveTyzwScpproQueryStatusReturnService(String topic, String content) {
        log.info("===TyzwJsReturnServiceImpl====",topic);
        TyzwJsReturn tyzwJsReturn = new TyzwJsReturn();
        JSONObject jsonObject = JSONObject.parseObject(content);
        tyzwJsReturn.setCommandId(jsonObject.getString("commandID"));
        tyzwJsReturn.setIp(jsonObject.getString("IP"));
        tyzwJsReturn.setDevId(jsonObject.getString("devId"));
        tyzwJsReturn.setType(jsonObject.getString("type"));
        tyzwJsReturn.setDateTime(dateFormatUtil.strDateTime(jsonObject.getString("dateTime")));
        tyzwJsReturn.setRetStatus(Integer.parseInt(jsonObject.getString("retStatus")));

        TyzwJsReturn result = tyzwJsReturnRepository.save(tyzwJsReturn);
        if (result.getId()!=null){
            return ResultEnum.SUCESS.getMessage();
        }else {
            return ResultEnum.FAILED.getMessage();
        }
    }
}
