package com.example.newone.mapper;

import com.example.newone.pojo.Users;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UsersMapper {


    @Select("SELECT * FROM users WHERE username = #{username} AND password = #{password}")
    Users getByUsernameAndPassword(@Param("username") String username, @Param("password") String password);


    // 查询所有用户
    @Select("select id,username,password,role,depts from Users")
    List<Users> list();

    // 删除用户
    @Select("delete from Users where id = #{id}")
    void delete(Integer id);

    //sql语句，这三个属性是必须要插入的
    // 新增用户
    @Select("insert into Users(username,password,role,depts) " +
            "values(#{username},#{password},#{role},#{depts})")
    void save(Users users);

    // 修改用户
    @Select("update Users set username = #{username},password = #{password}," +
            "role = #{role}," +
            "depts = #{depts} where id = #{id}")
    void update(Users users);
}
