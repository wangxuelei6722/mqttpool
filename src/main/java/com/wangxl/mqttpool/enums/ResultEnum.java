package com.wangxl.mqttpool.enums;

/**
 * @ClassName: ResultEnum
 * @Description:
 * @Author
 * @Date 2022/3/17
 * @Version 1.0
 */
public enum ResultEnum {

    SUCESS(1,"成功！"),
    FAILED(0,"失败！");

    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
