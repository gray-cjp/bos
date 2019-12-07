package com.cjp.action.Impl;

import com.cjp.domain.PageBean;
import com.cjp.domain.Staff;
import com.cjp.service.Impl.StaffServiceImpl;
import com.cjp.service.StaffService;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
@Scope("prototype")
public class StaffAction extends IBaseActionImpl<Staff> {
    private String ids;
    public void setIds(String ids) {
        this.ids = ids;
    }

    public String getIds() {
        return ids;
    }

    @Autowired
   private StaffService staffService;
    //收派员添加
    public String save(){
        staffService.save(model);
        return SUCCESS;
    }
    //分页查询
    public String pageQuery()  {
        staffService.pageQuery(pageBean);
        java2Json(pageBean,new String[]{"currentPage","detachedCriteria","pageSize"});
        return NONE;
    }
    //删除
    public String deleteBatch(){
        staffService.deleteBatch(ids);
        return "list";
    }
    //修改
    public  String edit(){
        Staff staff  =staffService.findById(getModel().getId());
        staff.setHaspda(model.getHaspda());
        staff.setName(model.getName());
        staff.setStandard(getModel().getStandard());
        staff.setStation(getModel().getStation());
        staff.setTelephone(getModel().getTelephone());
        staffService.update(staff);
        return "list";
    }
    public String listajax(){
        List<Staff> list =staffService.findListNotDelete();
        java2Json(list,new String[]{"decidedzones"});
        return NONE;
    }
}
