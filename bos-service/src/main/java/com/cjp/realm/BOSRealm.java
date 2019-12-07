package com.cjp.realm;

import com.cjp.dao.UserDao;
import com.cjp.domain.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class BOSRealm extends AuthorizingRealm {
    @Autowired
    private UserDao userDao;
    //授权方法
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermission("staff-list");
        info.addStringPermission("staff-delete");
        User user1 = (User) SecurityUtils.getSubject().getPrincipal();
        User user2 = (User) principalCollection.getPrimaryPrincipal();
        return info;
    }
    //认证方法
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken mytoken = (UsernamePasswordToken) authenticationToken;
        String username = mytoken.getUsername();
        //根据用户名查询数据库中的密码
        User user = userDao.findUserByUsername(username);
        if (user == null){
            return null;
        }
        AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user, user.getPassword(), this.getName());
        return authenticationInfo;
    }
}
