package com.wangxl.mqttpool.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.wangxl.mqttpool.enums.ResultEnum;
import com.wangxl.mqttpool.pojo.TyzwJsReturn;
import com.wangxl.mqttpool.pojo.TyzwScpproTimeInfoReturn;
import com.wangxl.mqttpool.repository.TyzwScpproTimeInfoReturnRepository;
import com.wangxl.mqttpool.service.TyzwScpproTimeInfoReturnService;
import com.wangxl.mqttpool.utils.DateFormatUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @ClassName: TyzwScpproTimeInfoReturnServiceImpl
 * @Description:
 * @Author
 * @Date 2022/3/24
 * @Version 1.0
 */
@Service
@Slf4j
public class TyzwScpproTimeInfoReturnServiceImpl implements TyzwScpproTimeInfoReturnService {

    @Autowired
    private TyzwScpproTimeInfoReturnRepository tyzwScpproTimeInfoReturnRepository;

    @Autowired
    private DateFormatUtil dateFormatUtil;
    @Override
    public String saveTyzwScpproTimeInfoReturnService(String topic, String content) {
        log.info("===TyzwScpproTimeInfoReturnServiceImpl====",topic);
        TyzwScpproTimeInfoReturn tyzwScpproTimeInfoReturn = new TyzwScpproTimeInfoReturn();
        JSONObject jsonObject = JSONObject.parseObject(content);
        tyzwScpproTimeInfoReturn.setCommandId(jsonObject.getString("commandID"));
        tyzwScpproTimeInfoReturn.setIp(jsonObject.getString("IP"));
        tyzwScpproTimeInfoReturn.setDevId(jsonObject.getString("devId"));
        tyzwScpproTimeInfoReturn.setType(jsonObject.getString("type"));
        tyzwScpproTimeInfoReturn.setDateTime(dateFormatUtil.strDateTime(jsonObject.getString("dateTime")));
       // tyzwScpproTimeInfoReturn.setRetStatus(Integer.parseInt(jsonObject.getString("retStatus")));
        tyzwScpproTimeInfoReturn.setRetStatus(0);
        tyzwScpproTimeInfoReturn.setCreateTime(new Date());

        TyzwScpproTimeInfoReturn result = tyzwScpproTimeInfoReturnRepository.save(tyzwScpproTimeInfoReturn);
        if (result.getId()!=null){
            return ResultEnum.SUCESS.getMessage();
        }else {
            return ResultEnum.FAILED.getMessage();
        }
    }
}
