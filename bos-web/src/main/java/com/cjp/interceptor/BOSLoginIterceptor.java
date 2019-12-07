package com.cjp.interceptor;

import com.cjp.domain.User;
import com.cjp.utils.BOSUtlis;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class BOSLoginIterceptor extends MethodFilterInterceptor {
    @Override
    protected String doIntercept(ActionInvocation invocation) throws Exception {
        User user = BOSUtlis.getLoginUser();
        if (user == null){
            return "login";
        }else {
            return invocation.invoke();
        }
    }
}
