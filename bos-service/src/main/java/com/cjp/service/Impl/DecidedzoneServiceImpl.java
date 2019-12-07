package com.cjp.service.Impl;

import com.cjp.dao.DecidedzoneDao;
import com.cjp.dao.SubareaDao;
import com.cjp.domain.Decidedzone;
import com.cjp.domain.PageBean;
import com.cjp.domain.Subarea;
import com.cjp.service.DecidedzoneService;
import com.cjp.service.SubareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DecidedzoneServiceImpl implements DecidedzoneService {
    @Autowired
    private DecidedzoneDao decidedzoneDao;
    @Autowired
    private SubareaDao subareaDao;
    @Override
    public void save(Decidedzone model, String... subareaid) {
        decidedzoneDao.save(model);
        for (String id : subareaid){
            Subarea subarea = subareaDao.findById(id);
            subarea.setDecidedzone(model);
        }
    }

    @Override
    public void pageQuery(PageBean pageBean) {
        decidedzoneDao.pageQuery(pageBean);
    }
}
