package com.wangxl.mqttpool.repository;

import com.wangxl.mqttpool.pojo.TyzwScpproCardLogReturn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TyzwScpproCardLogReturnRepository extends JpaRepository<TyzwScpproCardLogReturn,Integer> {
}
