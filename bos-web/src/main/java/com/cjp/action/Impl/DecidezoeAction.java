package com.cjp.action.Impl;

import com.cjp.domain.Decidedzone;
import com.cjp.service.DecidedzoneService;
import com.cjp.utils.Customer;
import com.cjp.utils.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@Scope("prototype")
public class DecidezoeAction extends IBaseActionImpl<Decidedzone> {
    private String[] subareaid;
    public void setSubareaid(String[] subareaid) {
        this.subareaid = subareaid;
    }
    @Autowired
    private DecidedzoneService decidedzoneService;
    @Autowired
    private CustomerService proxy;
    private List<Integer> customerIds;

    public void setCustomerIds(List<Integer> customerIds) {
        this.customerIds = customerIds;
    }

    public String add(){
        decidedzoneService.save(model,subareaid);
        return "list";
    }
    public String pageQuery(){
        decidedzoneService.pageQuery(pageBean);
        java2Json(pageBean,new String[]{"currentPage","detachedCriteria","pageSize","subareas","decidedzones"});
        return NONE;
    }
    public String findListNotAssociation(){
        List<Customer> listNotAssociation = proxy.findListNotAssociation();
        java2Json(listNotAssociation,new String[]{});
        return NONE;
    }
    public String findListHasAssociation(){
        String id = model.getId();
        List<Customer> listHasAssociation = proxy.findListHasAssociation(id);
        java2Json(listHasAssociation,new String[]{});
        return NONE;
    }
    public String assigncustomerstodecidedzone(){
        String id = model.getId();
        proxy.assigncustomerstodecidedzone(id,customerIds);
        return "list";
    }
}
