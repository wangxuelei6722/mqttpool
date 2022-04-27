package com.wangxl.mqttpool.pojo;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class TyzwScpproCardLogReturn {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String commandId;
  private String ip;
  private String devId;
  private String type;
  private Date dateTime;
  private Integer rfidNumber;
  private String carType;
  private String devInfo;
  private String state;
  private Date payCardTime;
  private String devstatus;
  private Date createTime;

}
