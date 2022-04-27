package com.wangxl.mqttpool.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.wangxl.mqttpool.enums.ResultEnum;
import com.wangxl.mqttpool.pojo.TyzwScpproEnergyClearZeroReturn;
import com.wangxl.mqttpool.pojo.TyzwScpproModifyNameReturn;
import com.wangxl.mqttpool.repository.TyzwScpproModifyNameReturnRepository;
import com.wangxl.mqttpool.service.TyzwScpproModifyNameReturnService;
import com.wangxl.mqttpool.utils.DateFormatUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @ClassName: TyzwScpproModifyNameReturnServiceImpl
 * @Description:
 * @Author
 * @Date 2022/3/24
 * @Version 1.0
 */
@Service
@Slf4j
public class TyzwScpproModifyNameReturnServiceImpl implements TyzwScpproModifyNameReturnService {

    @Autowired
    private TyzwScpproModifyNameReturnRepository tyzwScpproModifyNameReturnRepository;
    @Autowired
    private DateFormatUtil dateFormatUtil;
    @Override
    public String saveTyzwScpproModifyNameReturnService(String topic, String content) {
        log.info("==========TyzwScpproEnergyClearZeroReturnServiceImpl=======",topic);
        TyzwScpproModifyNameReturn tyzwScpproModifyNameReturn = new TyzwScpproModifyNameReturn();
        JSONObject jsonObject = JSONObject.parseObject(content);
        tyzwScpproModifyNameReturn.setCommandId(jsonObject.getString("commandID"));
        tyzwScpproModifyNameReturn.setIp(jsonObject.getString("IP"));
        tyzwScpproModifyNameReturn.setDevId(jsonObject.getString("devId"));
        tyzwScpproModifyNameReturn.setType(jsonObject.getString("type"));
        tyzwScpproModifyNameReturn.setDateTime(dateFormatUtil.strDateTime(jsonObject.getString("dateTime")));
        tyzwScpproModifyNameReturn.setName(jsonObject.getString("name"));
        tyzwScpproModifyNameReturn.setCreateTime(new Date());

        TyzwScpproModifyNameReturn result = tyzwScpproModifyNameReturnRepository.save(tyzwScpproModifyNameReturn);
        if (result.getId()!=null){
            return ResultEnum.SUCESS.getMessage();
        }else {
            return ResultEnum.FAILED.getMessage();
        }
    }
}
