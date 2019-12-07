package com.cjp.service;

import com.cjp.domain.User;

public interface UserService {
    User login(User model);

    void editPassword(String id, String password);
}
