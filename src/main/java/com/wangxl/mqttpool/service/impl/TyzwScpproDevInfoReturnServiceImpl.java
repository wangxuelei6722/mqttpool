package com.wangxl.mqttpool.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.wangxl.mqttpool.enums.ResultEnum;
import com.wangxl.mqttpool.pojo.TyzwJsReturn;
import com.wangxl.mqttpool.pojo.TyzwScpproDevInfoReturn;
import com.wangxl.mqttpool.repository.TyzwScpproDevInfoReturnRepostory;
import com.wangxl.mqttpool.service.TyzwScpproDevInfoReturnService;
import com.wangxl.mqttpool.utils.DateFormatUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: TyzwScpproDevInfoReturnServiceImpl
 * @Description:
 * @Author
 * @Date 2022/3/24
 * @Version 1.0
 */
@Service
@Slf4j
public class TyzwScpproDevInfoReturnServiceImpl implements TyzwScpproDevInfoReturnService {

    @Autowired
    private TyzwScpproDevInfoReturnRepostory tyzwScpproDevInfoReturnRepostory;

    @Autowired
    private DateFormatUtil dateFormatUtil;
    @Override
    public String saveTyzwScpproDevInfoReturnService(String topic, String content) {

        log.info("===TyzwScpproDevInfoReturnServiceImpl====",topic);
        TyzwScpproDevInfoReturn tyzwScpproDevInfoReturn = new TyzwScpproDevInfoReturn();
        JSONObject jsonObject = JSONObject.parseObject(content);
        tyzwScpproDevInfoReturn.setCommandId(jsonObject.getString("commandID"));
        tyzwScpproDevInfoReturn.setIp(jsonObject.getString("IP"));
        tyzwScpproDevInfoReturn.setDevId(jsonObject.getString("devId"));
        tyzwScpproDevInfoReturn.setType(jsonObject.getString("type"));
        tyzwScpproDevInfoReturn.setDateTime(dateFormatUtil.strDateTime(jsonObject.getString("dateTime")));
        //tyzwScpproDevInfoReturn.setRetStatus(Integer.parseInt(jsonObject.getString("retStatus")));
        //retStatus 返回有空格
        tyzwScpproDevInfoReturn.setRetStatus(0);

        TyzwScpproDevInfoReturn result = tyzwScpproDevInfoReturnRepostory.save(tyzwScpproDevInfoReturn);
        if (result.getId()!=null){
            return ResultEnum.SUCESS.getMessage();
        }else {
            return ResultEnum.FAILED.getMessage();
        }
    }
}
