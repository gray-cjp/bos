package com.cjp.service;

import com.cjp.domain.PageBean;
import com.cjp.domain.Subarea;
import com.cjp.utils.Customer;

import java.util.List;

public interface SubareaService {
    void add(Subarea model);

    void pageQuery(PageBean pageBean);

    List<Subarea> findAll();

    List<Subarea> findListNotAssociation();

    List<Subarea> findListByDecidedzoneId(String decidedzoneId);
}
