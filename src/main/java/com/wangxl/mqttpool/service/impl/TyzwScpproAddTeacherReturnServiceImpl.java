package com.wangxl.mqttpool.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.wangxl.mqttpool.enums.ResultEnum;
import com.wangxl.mqttpool.pojo.TyzwScpproAddTeacherReturn;
import com.wangxl.mqttpool.repository.TyzwScpproAddTeacherReturnRepository;
import com.wangxl.mqttpool.service.TyzwScpproAddTeacherReturnService;
import com.wangxl.mqttpool.utils.DateFormatUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @ClassName: TyzwScpproAddTeacherReturnServiceImpl
 * @Description:
 * @Author
 * @Date 2022/3/24
 * @Version 1.0
 */
@Service
@Slf4j
public class TyzwScpproAddTeacherReturnServiceImpl implements TyzwScpproAddTeacherReturnService {

    @Autowired
    private TyzwScpproAddTeacherReturnRepository tyzwScpproAddTeacherReturnRepository;
    @Autowired
    private DateFormatUtil dateFormatUtil;
    @Override
    public String saveTyzwScpproAddTeacherReturnService(String topic, String content) {
        log.info("===TyzwScpproAddTeacherReturnServiceImpl====",topic);

        TyzwScpproAddTeacherReturn tyzwScpproAddTeacherReturn = new TyzwScpproAddTeacherReturn();
        JSONObject jsonObject = JSONObject.parseObject(content);
        tyzwScpproAddTeacherReturn.setCommandId(jsonObject.getString("commandID"));
        tyzwScpproAddTeacherReturn.setIp(jsonObject.getString("IP"));
        tyzwScpproAddTeacherReturn.setDevId(jsonObject.getString("devId"));
        tyzwScpproAddTeacherReturn.setType(jsonObject.getString("type"));
        tyzwScpproAddTeacherReturn.setDevCOUNT(jsonObject.getString("devCOUNT"));
        tyzwScpproAddTeacherReturn.setDateTime(dateFormatUtil.strDateTime(jsonObject.getString("dateTime")));
      //  tyzwScpproAddTeacherReturn.setRetStatus(Integer.parseInt(jsonObject.getString("retStatus")));
        tyzwScpproAddTeacherReturn.setRetStatus(0);
        tyzwScpproAddTeacherReturn.setCreateTime(new Date());
        TyzwScpproAddTeacherReturn result = tyzwScpproAddTeacherReturnRepository.save(tyzwScpproAddTeacherReturn);
        if (result.getId()!=null){
            return ResultEnum.SUCESS.getMessage();
        }else {
            return ResultEnum.FAILED.getMessage();
        }
    }
}
