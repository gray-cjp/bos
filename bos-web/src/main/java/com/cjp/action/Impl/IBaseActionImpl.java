package com.cjp.action.Impl;

import com.cjp.domain.PageBean;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class IBaseActionImpl<T> extends ActionSupport implements ModelDriven<T> {
    protected T model;
//    protected int page;
//    protected int rows;
    protected PageBean pageBean = new PageBean();
    protected DetachedCriteria detachedCriteria=null;
    public void setPage(int page) {
       pageBean.setCurrPage(page);
    }

    public void setRows(int rows) {
        pageBean.setPageSize(rows);
    }

    @Override
    public T getModel() {
        return model;
    }
    public IBaseActionImpl(){
        ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
        Type[] actualTypeArguments = genericSuperclass.getActualTypeArguments();
        if (actualTypeArguments != null){
            Class<T> entity = (Class<T>) actualTypeArguments[0];
            detachedCriteria = DetachedCriteria.forClass(entity);
            pageBean.setDetachedCriteria(detachedCriteria);
            try {
                model = entity.newInstance();
            } catch (InstantiationException e) {
                System.out.println(e);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
    }
    public void java2Json(Object o,String[] exclueds){
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(exclueds);
        String string = JSONObject.fromObject(o, jsonConfig).toString();
        ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
        try {
            ServletActionContext.getResponse().getWriter().write(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void java2Json(List o, String[] exclueds){
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(exclueds);
        String string = JSONArray.fromObject(o, jsonConfig).toString();
        ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
        try {
            ServletActionContext.getResponse().getWriter().write(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
