package com.wangxl.mqttpool.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class NstrSubscriptionResults  {

	@Id
	private Integer id;
	//主题
	private String topic;
	//uuid转换的唯一数据，不能重复。此字段用字符串表示
	private String  commandID;
	//设备的ip地址，不能ip冲突，底层可修改
	private String  IP;
	//设备的唯一识别码，服务器识别的唯一标准
	private String  devId;
	//说明此topic的类型
	private String  type;
	//添加卡片数量
	private String  devCOUNT;
	//卡号
	private String rfidNumber;
	//发布topic的时间
	private String  dateTime;
	//刷卡时间
	private String  time;
	//卡片类型
	private String  carType;
	//0代表命令解析正确，1表示命令解析错误，对接有协议问题
	private Integer  retStatus;
	//老师和无效卡显示空    设备状态（有效卡0表示关闭，1表示打开，无效卡2，未到点3，设备异常4）
	private Integer  devstatus;
	//是否是预约时段
	private String  state;
	//0表示关，1表示开
	private Integer status;
	//刷卡取电
	private String name;
	//在线状态0离线、1在线
	private Integer isOnline;
	//电流值
	private Integer current;
	//电压值
	private Integer voltage;
	//有功功率
	private Integer power;
	//有功总电能
	private Integer energy;
	//功率因素
	private double powerFacto;

	
}
