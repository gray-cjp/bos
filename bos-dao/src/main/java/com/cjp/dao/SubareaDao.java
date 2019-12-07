package com.cjp.dao;

import com.cjp.domain.Subarea;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

public interface SubareaDao extends IBaseDao<Subarea>{
    List<Subarea> findListNotAssociation(DetachedCriteria detachedCriteria);
}
