package com.movitech.{{ project }}.core.user.service;

import com.movitech.{{ project }}.core.log.Logging;
import com.movitech.{{ project }}.core.user.dao.UserDao;
import com.movitech.{{ project }}.core.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserDao dao;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<User> getUsers() {
        List<User> list = new ArrayList<>();
        for (User user : dao.findAll()) {
            list.add(user);
        }
        return list;
    }

    public User addUser(User user) {
        return dao.save(user);
    }

    public User getUser(String id) {
        return dao.findOne(id);
    }

    public User getUserByName(String name) {
        return dao.findByUsername(name);
    }

    @Logging
    public User register(String username, String password) {
        User existed = dao.findByUsername(username);
        if (existed != null) throw new IllegalStateException("USERNAME ALREADY EXIST");
        User user = new User();
        user.setUsername(username);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        return dao.save(user);
    }
}