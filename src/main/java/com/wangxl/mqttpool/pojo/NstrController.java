package com.wangxl.mqttpool.pojo;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * @ClassName: NstrController
 * @Description: 控制器pojo
 * @Author
 * @Date 2022/3/31
 * @Version 1.0
 */
@Data
@Entity
public class NstrController {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String controller_id;

    private Integer controller_kind_id;
    private Integer unit;
    private Integer controller_type_id;
    private Integer manage_person;
    private Integer controller_manu;
    private String controller_location;
    private String IP;
    private Integer status;
    private Integer repair_status;
    private Integer site_status;
    private Date insert_time;
    private Date update_time;
    private Integer user_id;


}
