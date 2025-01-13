package com.example.newone.service;

import com.example.newone.pojo.Users;

import java.util.List;

public interface UsersService {
    List<Users> list();

    void delete(Integer id);

    void save(Users users);

    void update(Users users);

//    Users login(Users users);

    Users login(String username, String password);
}
