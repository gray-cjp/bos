package com.cjp.dao.Impl;

import com.cjp.dao.StaffDao;
import com.cjp.domain.Staff;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StaffDaoImpl extends IBaseDaoImpl<Staff> implements StaffDao {
    @Override
    public List<Staff> findListNotDelete(DetachedCriteria detachedCriteria) {
        return (List<Staff>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
    }
}
