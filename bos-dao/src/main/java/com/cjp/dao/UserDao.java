package com.cjp.dao;

import com.cjp.domain.User;

public interface UserDao extends IBaseDao<User>{
    User findUserByUsernameAndPassword(String username, String password);
}
