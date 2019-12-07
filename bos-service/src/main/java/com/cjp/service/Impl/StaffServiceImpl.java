package com.cjp.service.Impl;

import com.cjp.dao.Impl.StaffDaoImpl;
import com.cjp.dao.StaffDao;
import com.cjp.domain.PageBean;
import com.cjp.domain.Staff;
import com.cjp.service.StaffService;
import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StaffServiceImpl implements StaffService {
    @Autowired
    private StaffDao staffDao;
    @Override
    public void save(Staff model) {
        staffDao.save(model);
    }

    @Override
    public void pageQuery(PageBean pageBean) {
        staffDao.pageQuery(pageBean);
    }

    @Override
    public void deleteBatch(String ids) {
        if (StringUtils.isNotBlank(ids)) {
            String[] staffIds = ids.split(",");
            for (String id : staffIds) {
                staffDao.executeUpdate("staff.delete", id);
            }
        }
    }

    @Override
    public Staff findById(String id) {
        return staffDao.findById(id);
    }

    @Override
    public void update(Staff staff) {
        staffDao.update(staff);
    }

    @Override
    public List<Staff> findListNotDelete() {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Staff.class);
        detachedCriteria.add(Restrictions.eq("deltag","0"));
        return staffDao.findListNotDelete(detachedCriteria);
    }
}
