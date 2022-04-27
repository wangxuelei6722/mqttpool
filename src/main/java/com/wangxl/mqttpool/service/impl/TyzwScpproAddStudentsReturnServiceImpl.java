package com.wangxl.mqttpool.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.wangxl.mqttpool.enums.ResultEnum;
import com.wangxl.mqttpool.pojo.TyzwScpproAddStudentsReturn;
import com.wangxl.mqttpool.pojo.TyzwScpproAddTeacherReturn;
import com.wangxl.mqttpool.repository.TyzwScpproAddStudentsReturnRepository;
import com.wangxl.mqttpool.service.TyzwScpproAddStudentsReturnService;
import com.wangxl.mqttpool.utils.DateFormatUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @ClassName: TyzwScpproAddStudentsReturnServiceImpl
 * @Description:
 * @Author
 * @Date 2022/3/24
 * @Version 1.0
 */
@Service
@Slf4j
public class TyzwScpproAddStudentsReturnServiceImpl implements TyzwScpproAddStudentsReturnService {

    @Autowired
    private TyzwScpproAddStudentsReturnRepository tyzwScpproAddStudentsReturnRepository;
    @Autowired
    private DateFormatUtil dateFormatUtil;
    @Override
    public String saveTyzwScpproAddStudentsReturnService(String topic, String content) {
        log.info("==========TyzwScpproAddStudentsReturnServiceImpl=========",topic);
        TyzwScpproAddStudentsReturn tyzwScpproAddStudentsReturn = new TyzwScpproAddStudentsReturn();
        JSONObject jsonObject = JSONObject.parseObject(content);
        tyzwScpproAddStudentsReturn.setCommandId(jsonObject.getString("commandID"));
        tyzwScpproAddStudentsReturn.setIp(jsonObject.getString("IP"));
        tyzwScpproAddStudentsReturn.setDevId(jsonObject.getString("devId"));
        tyzwScpproAddStudentsReturn.setType(jsonObject.getString("type"));
        tyzwScpproAddStudentsReturn.setDevCOUNT(jsonObject.getString("devCOUNT"));
        tyzwScpproAddStudentsReturn.setDateTime(dateFormatUtil.strDateTime(jsonObject.getString("dateTime")));
        //  tyzwScpproAddTeacherReturn.setRetStatus(Integer.parseInt(jsonObject.getString("retStatus")));
        tyzwScpproAddStudentsReturn.setRetStatus(0);
        tyzwScpproAddStudentsReturn.setCreateTime(new Date());

        TyzwScpproAddStudentsReturn result = tyzwScpproAddStudentsReturnRepository.save(tyzwScpproAddStudentsReturn);
        if (result.getId()!=null){
            return ResultEnum.SUCESS.getMessage();
        }else {
            return ResultEnum.FAILED.getMessage();
        }
    }
}
