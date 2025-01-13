package com.example.newone.controller;

import com.example.newone.pojo.Result;
import com.example.newone.pojo.Users;
import com.example.newone.service.UsersService;


import com.example.newone.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    private UsersService usersService;

    @PostMapping("/login")
    public Result login(@RequestBody Users users) {
        log.info("Login attempt: {}", users);

        // 使用用户名和密码查询用户信息
        Users user = usersService.login(users.getUsername(), users.getPassword());

        if (user != null) {
            // 查询成功，返回完整的用户信息
            log.info("User logged in: {}", user);

            Map<String, Object> claims = new HashMap<>();
            claims.put("id", user.getId());
            claims.put("role", user.getRole());
            claims.put("username", user.getUsername());

            String jwt = JwtUtils.generateJwt(claims);
            return Result.success(jwt);
        }

        return Result.error("用户名或密码错误");
    }

}
