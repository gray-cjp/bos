package com.cjp.utils;

import com.cjp.domain.User;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpSession;

public class BOSUtlis {
    public static HttpSession getSession(){
        return  ServletActionContext.getRequest().getSession();
    }
    public static User getLoginUser(){
        return (User) getSession().getAttribute("loginUser");
    }
}
