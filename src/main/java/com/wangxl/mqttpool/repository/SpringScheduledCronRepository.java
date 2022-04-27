package com.wangxl.mqttpool.repository;

import com.wangxl.mqttpool.pojo.SpringScheduledCron;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringScheduledCronRepository extends JpaRepository<SpringScheduledCron,Integer> {
}
