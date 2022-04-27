package com.wangxl.mqttpool.utils;

import java.util.UUID;

/**
 * @ClassName: StringUtil
 * @Description: 字符串工具类
 * @Author
 * @Date 2022/3/31
 * @Version 1.0
 */
public class UUIDutil {

    /**
     * 获取随机数据字符串
     */
    public String getUUID(){

        return UUID.randomUUID().toString().replace("-","");
    }
}
