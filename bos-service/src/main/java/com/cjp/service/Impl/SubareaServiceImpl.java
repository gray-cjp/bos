package com.cjp.service.Impl;

import com.cjp.dao.SubareaDao;
import com.cjp.domain.PageBean;
import com.cjp.domain.Subarea;
import com.cjp.service.SubareaService;
import com.cjp.utils.Customer;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SubareaServiceImpl implements SubareaService {
    @Autowired
    private SubareaDao subareaDao;

    @Override
    public void add(Subarea model) {
        subareaDao.save(model);
    }

    @Override
    public void pageQuery(PageBean pageBean) {
        subareaDao.pageQuery(pageBean);
    }

    @Override
    public List<Subarea> findAll() {
        return subareaDao.findById();
    }

    @Override
    public List<Subarea> findListNotAssociation() {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Subarea.class);
        detachedCriteria.add(Restrictions.isNull("decidedzone"));
        return subareaDao.findListNotAssociation(detachedCriteria);
    }

    @Override
    public List<Subarea> findListByDecidedzoneId(String decidedzoneId) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Subarea.class);
        detachedCriteria.add(Restrictions.eq("decidedzone.id",decidedzoneId));
        return subareaDao.findListNotAssociation(detachedCriteria);
    }
}
