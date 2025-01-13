package com.example.newone.service;


import com.example.newone.pojo.Equipment;

import java.util.List;

public interface EquipmentService {
    List<Equipment> list();

    void delete(Integer id);

    void save(Equipment equipment);

    void update(Equipment equipment);
}

