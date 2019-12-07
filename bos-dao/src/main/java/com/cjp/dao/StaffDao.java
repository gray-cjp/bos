package com.cjp.dao;

import com.cjp.domain.Staff;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

public interface StaffDao extends IBaseDao<Staff> {
    List<Staff> findListNotDelete(DetachedCriteria detachedCriteria);
}
