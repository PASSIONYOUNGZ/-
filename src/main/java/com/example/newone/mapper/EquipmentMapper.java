package com.example.newone.mapper;

import com.example.newone.pojo.Equipment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EquipmentMapper {

    // 查询所有部门
    @Select("select * from equipment")
    List<Equipment> list();

    // 删除部门
    @Select("delete from equipment where id = #{id}")
    void delete(Integer id);

    //sql语句，这三个属性是必须要插入的
    // 新增部门
    @Select("insert into equipment(name,status,description,create_time,update_time) " +
            "values(#{name},#{status},#{description},#{createTime},#{updateTime})")
    void save(Equipment equipment);

    // 修改部门
    @Select("update equipment set name = #{name},status = #{status},description = #{description}," +
            "update_time = #{updateTime} where id = #{id}")
    void update(Equipment equipment);
}
