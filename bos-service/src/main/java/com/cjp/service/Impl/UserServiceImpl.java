package com.cjp.service.Impl;

import com.cjp.dao.UserDao;
import com.cjp.domain.User;
import com.cjp.service.UserService;
import com.cjp.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User login(User model) {
        System.out.println("service");
        String password = MD5Utils.md5(model.getPassword());
        return userDao.findUserByUsernameAndPassword(model.getUsername(),password);
    }

    @Override
    public void editPassword(String id, String password) {
        password = MD5Utils.md5(password);
        userDao.executeUpdate("user.editPassword",password,id);
    }

}
