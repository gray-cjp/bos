package com.cjp.action.Impl;

import com.cjp.domain.Noticebill;
import com.cjp.service.NoticebillService;
import com.cjp.utils.Customer;
import com.cjp.utils.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("prototype")
public class NoticebillAction extends IBaseActionImpl<Noticebill> {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private NoticebillService noticebillService;
    public String findCustomerByTelephone(){
        Customer customer = customerService.findCustomerByTelephone(model.getTelephone());
        java2Json(customer,new String[]{});
        return NONE;
    }
    public String add(){
        noticebillService.save(model);
        return NONE;
    }
}
