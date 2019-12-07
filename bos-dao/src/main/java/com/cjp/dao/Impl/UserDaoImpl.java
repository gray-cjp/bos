package com.cjp.dao.Impl;

import com.cjp.dao.IBaseDao;
import com.cjp.dao.UserDao;
import com.cjp.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class UserDaoImpl extends IBaseDaoImpl<User> implements UserDao{
    @Override
    public User findUserByUsernameAndPassword(String username, String password) {
        String hql = "FROM User WHERE username = ? AND password = ?";
        List<User> list = (List<User>) this.getHibernateTemplate().find(hql, username, password);
        if (list.size() != 0 && list != null){
            return list.get(0);
        }
        return null;
    }
}
