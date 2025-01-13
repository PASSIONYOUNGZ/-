package com.example.newone.controller;

import com.example.newone.pojo.Result;
import com.example.newone.pojo.Users;
import com.example.newone.service.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class UsersController {

    @Autowired
    private UsersService usersService;

    //用户查询功能
    @GetMapping("/users")
    public Result list(){
        log.info("查询所有用户");
        List<Users> usersList = usersService.list();
        return Result.success(usersList);
    }

    @DeleteMapping("/users/{id}")
    //用这个注解来获取路径中的参数 @PathVariable
    public Result delete(@PathVariable Integer id){
        log.info("删除用户");
        usersService.delete(id);
        return Result.success();
    }

    //新增用户
    //用这个注解来获取前端传过来的json数据 @RequestBody
    @PostMapping("/users")
    public Result save(@RequestBody Users users){
        log.info("新增用户");
        usersService.save(users);
        return Result.success();
    }

    //修改用户
    @PutMapping("/users")
    public Result update(@RequestBody Users users){
        log.info("修改用户");
        usersService.update(users);
        return Result.success();
    }
}
