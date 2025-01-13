package com.example.newone.service.impl;

import com.example.newone.mapper.EquipmentMapper;
import com.example.newone.pojo.Equipment;
import com.example.newone.service.EquipmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


import java.util.List;

@Slf4j
@Service
public class EquipmentServiceImpl implements EquipmentService {

    @Autowired
    private EquipmentMapper equipmentMapper;

    @Override
    public List<Equipment> list() {
        return equipmentMapper.list();
    }

    @Override
    public void delete(Integer id) {
        equipmentMapper.delete(id);
    }

    @Override
    public void save(Equipment equipment) {
        equipment.setCreateTime(LocalDateTime.now());
        equipment.setUpdateTime(LocalDateTime.now());
        equipmentMapper.save(equipment);
    }

    @Override
    public void update(Equipment equipment) {
        equipment.setUpdateTime(LocalDateTime.now());
        equipment.setCreateTime(LocalDateTime.now());
        equipmentMapper.update(equipment);
    }


}
