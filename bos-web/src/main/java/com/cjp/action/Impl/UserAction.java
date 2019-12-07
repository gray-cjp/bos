package com.cjp.action.Impl;

import com.cjp.dao.Impl.IBaseDaoImpl;
import com.cjp.domain.User;
import com.cjp.service.UserService;
import com.cjp.utils.BOSUtlis;
import com.cjp.utils.Customer;
import com.cjp.utils.CustomerService;
import com.cjp.utils.MD5Utils;
import com.opensymphony.xwork2.ActionContext;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.List;

@Controller
@Scope("prototype")
public class UserAction extends IBaseActionImpl<User> {
    private String checkcode;
    public void setCheckcode(String checkcode) {
        this.checkcode = checkcode;
    }
    @Autowired
    private UserService service;
    public String login(){
        String key = (String) ServletActionContext.getRequest().getSession().getAttribute("key");
        if (checkcode.equals(key) && StringUtils.isNotBlank(checkcode)){
            //验证码输入正确
            //使用shiro框架进行认证
            Subject subject = SecurityUtils.getSubject();//获得当前用户对象，状态为“未认证”
            //用户名密码令牌
            AuthenticationToken token = new UsernamePasswordToken(model.getUsername(), MD5Utils.md5(model.getPassword()));
            try{
                subject.login(token);
                User user = (User) subject.getPrincipal();// 从认证信息获取user对象，用于登录存入session
                ServletActionContext.getRequest().getSession().setAttribute("loginUser",user);
            }catch (Exception e){
                System.out.println(e);
                this.addActionError("用户名或者密码输入错误！");
                return LOGIN;
            }
            return "home";

//            System.out.println("action");
//            User user = service.login(getModel());
//            if (user != null){
//                ServletActionContext.getRequest().getSession().setAttribute("loginUser",user);
//                return "home";
//            }else {
//                this.addActionError("用户名或者密码输入错误！");
//                return LOGIN;
//            }
        }else {
            this.addActionError("输入的验证码错误！");
            return LOGIN;
        }
    }
    //账户注销
    public String logout(){
        ServletActionContext.getRequest().getSession().invalidate();
        return LOGIN;
    }
    //修改密码
    public String editPassword() throws IOException {
        String f= "1";
        User user = BOSUtlis.getLoginUser();
        //通过捕获异常来判断密码是否修改成功
        try {
            service.editPassword(user.getId(),getModel().getPassword());
        }catch (Exception e){
            f = "0";
            System.out.println(e);
        }
        ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
        ServletActionContext.getResponse().getWriter().write(f);
        return NONE;
    }
}
