package com.cjp.action.Impl;

import com.cjp.domain.Function;
import com.cjp.service.FunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@Scope("prototype")
public class FunctionAction extends IBaseActionImpl<Function> {
@Autowired
private FunctionService functionService;
public String listajax(){
    List<Function> list = functionService.findAll();
    this.java2Json(list,new String[]{"roles","children","parentFunction"});
    return NONE;
}

}
