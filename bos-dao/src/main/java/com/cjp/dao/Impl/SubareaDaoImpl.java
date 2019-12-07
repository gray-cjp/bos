package com.cjp.dao.Impl;

import com.cjp.dao.SubareaDao;
import com.cjp.domain.Subarea;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SubareaDaoImpl extends IBaseDaoImpl<Subarea> implements SubareaDao {
    @Override
    public List<Subarea> findListNotAssociation(DetachedCriteria detachedCriteria) {
        return (List<Subarea>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
    }
}
