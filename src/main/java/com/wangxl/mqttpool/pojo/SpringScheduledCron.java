package com.wangxl.mqttpool.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @ClassName: SpringScheduledCron
 * @Description: 定时任务pojo
 * @Author
 * @Date 2022/3/30
 * @Version 1.0
 */
@Entity
@Data
public class SpringScheduledCron {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cron_id;
    private String cron_key;
    private String cron_expression;
    private String task_explain;
    private byte status;



}
