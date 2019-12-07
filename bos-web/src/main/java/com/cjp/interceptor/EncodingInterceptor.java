package com.cjp.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.StrutsStatics;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Map;

public class EncodingInterceptor extends AbstractInterceptor {
    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        ActionContext actionContext = invocation.getInvocationContext();
        HttpServletRequest request= (HttpServletRequest) actionContext.get(StrutsStatics.HTTP_REQUEST);
        System.out.println("Encoding Intercept...");
        /**
         * 此方法体对GET 和 POST方法均可
         */
        if( request.getMethod().compareToIgnoreCase("post")>=0){
            try {
                request.setCharacterEncoding("UTF-8");
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }else{
            Iterator iter=request.getParameterMap().values().iterator();
            while(iter.hasNext())
            {
                String[] parames=(String[])iter.next();
                for (int i = 0; i < parames.length; i++) {
                    try {
                        parames[i]=new String(parames[i].getBytes("iso8859-1"),"UTF-8");//此处GBK与页面编码一样
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return invocation.invoke();
    }
}
