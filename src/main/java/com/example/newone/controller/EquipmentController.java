package com.example.newone.controller;

import com.example.newone.pojo.Equipment;
import com.example.newone.pojo.Result;
import com.example.newone.service.EquipmentService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;


    //设备查询功能
    @GetMapping("/equip")
    public Result list(){
        log.info("查询所有设备");
        List<Equipment> equipmentList = equipmentService.list();
        return Result.success(equipmentList);
    }

    @DeleteMapping("/equip/{id}")
    //用这个注解来获取路径中的参数 @PathVariable
    public Result delete(@PathVariable Integer id){
        log.info("删除设备");
        equipmentService.delete(id);
        return Result.success();
    }

    //新增部门
    //用这个注解来获取前端传过来的json数据 @RequestBody
    @PostMapping("/equip")
    public Result save(@RequestBody Equipment equipment){
        log.info("新增设备");
        equipmentService.save(equipment);
        return Result.success();
    }

    //修改部门
    @PutMapping("/equip")
    public Result update(@RequestBody Equipment equipment){
        log.info("修改设备");
        equipmentService.update(equipment);
        return Result.success();
    }
}
