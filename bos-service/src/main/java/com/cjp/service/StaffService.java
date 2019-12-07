package com.cjp.service;

import com.cjp.domain.PageBean;
import com.cjp.domain.Staff;

import java.util.List;

public interface StaffService {
    void save(Staff model);

    void pageQuery(PageBean pageBean);

    void deleteBatch(String ids);

    Staff findById(String id);

    void update(Staff staff);

    List<Staff> findListNotDelete();
}
