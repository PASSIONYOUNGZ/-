package com.example.newone.service.impl;

import com.example.newone.mapper.UsersMapper;
import com.example.newone.pojo.Equipment;
import com.example.newone.pojo.Users;
import com.example.newone.service.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UsersMapper usersMapper;

    @Override
    public List<Users> list() {
        return usersMapper.list();
    }

    @Override
    public void delete(Integer id) {
        usersMapper.delete(id);
    }

    @Override
    public void save(Users users) {
        usersMapper.save(users);
    }

    @Override
    public void update(Users users) {
        usersMapper.update(users);
    }

    @Override
    public Users login(String username, String password) {
        return usersMapper.getByUsernameAndPassword(username, password);
    }

//    @Override
//    public Users login(Users users) {
//
//    }

}
