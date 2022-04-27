package com.wangxl.mqttpool.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.wangxl.mqttpool.enums.ResultEnum;
import com.wangxl.mqttpool.pojo.TyzwScpproEnergyClearZeroReturn;
import com.wangxl.mqttpool.repository.TyzwScpproEnergyClearZeroReturnRepository;
import com.wangxl.mqttpool.service.TyzwScpproEnergyClearZeroReturnService;
import com.wangxl.mqttpool.utils.DateFormatUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @ClassName: TyzwScpproEnergyClearZeroReturnServiceImpl
 * @Description:
 * @Author
 * @Date 2022/3/24
 * @Version 1.0
 */
@Service
@Slf4j
public class TyzwScpproEnergyClearZeroReturnServiceImpl implements TyzwScpproEnergyClearZeroReturnService {

    @Autowired
    private TyzwScpproEnergyClearZeroReturnRepository tyzwScpproEnergyClearZeroReturnRepository;
    @Autowired
    private DateFormatUtil dateFormatUtil;
    @Override
    public String saveTyzwScpproEnergyClearZeroReturnService(String topic, String content) {
        log.info("==========TyzwScpproEnergyClearZeroReturnServiceImpl=======",topic);
        TyzwScpproEnergyClearZeroReturn tyzwScpproEnergyClearZeroReturn = new TyzwScpproEnergyClearZeroReturn();
        JSONObject jsonObject = JSONObject.parseObject(content);
        tyzwScpproEnergyClearZeroReturn.setCommandId(jsonObject.getString("commandID"));
        tyzwScpproEnergyClearZeroReturn.setIp(jsonObject.getString("IP"));
        tyzwScpproEnergyClearZeroReturn.setDevId(jsonObject.getString("devId"));
        tyzwScpproEnergyClearZeroReturn.setType(jsonObject.getString("type"));
        tyzwScpproEnergyClearZeroReturn.setDateTime(dateFormatUtil.strDateTime(jsonObject.getString("dateTime")));
        // tyzwScpproTimeInfoReturn.setRetStatus(Integer.parseInt(jsonObject.getString("retStatus")));
        tyzwScpproEnergyClearZeroReturn.setRetStatus(0);
        tyzwScpproEnergyClearZeroReturn.setCreateTime(new Date());

        TyzwScpproEnergyClearZeroReturn result = tyzwScpproEnergyClearZeroReturnRepository.save(tyzwScpproEnergyClearZeroReturn);
        if (result.getId()!=null){
            return ResultEnum.SUCESS.getMessage();
        }else {
            return ResultEnum.FAILED.getMessage();
        }
    }
}
