package com.wangxl.mqttpool.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class TyzwJsReturn {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String commandId;
  private String ip;
  private String devId;
  private String type;
  private Date dateTime;
  private Integer retStatus;
  private Date createTime;


}
