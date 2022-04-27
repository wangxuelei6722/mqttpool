package com.wangxl.mqttpool.service.impl;

import com.wangxl.mqttpool.pojo.NstrController;
import com.wangxl.mqttpool.repository.NstrControllerRepository;
import com.wangxl.mqttpool.service.NstrControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: NstrControllerServiceImpl
 * @Description: 控制器实现类
 * @Author
 * @Date 2022/3/31
 * @Version 1.0
 */
@Service
public class NstrControllerServiceImpl implements NstrControllerService {

    @Autowired
    private NstrControllerRepository nstrControllerRepository;

    @Override
    public List<NstrController> findNstrControllerList() {

        return nstrControllerRepository.findAll();
    }
}
