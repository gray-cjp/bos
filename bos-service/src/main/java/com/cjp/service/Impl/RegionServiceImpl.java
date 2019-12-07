package com.cjp.service.Impl;

import com.cjp.dao.RegionDao;
import com.cjp.domain.PageBean;
import com.cjp.domain.Region;
import com.cjp.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RegionServiceImpl implements RegionService {
    @Autowired
    private RegionDao regionDao;
    @Override
    public void saveBatch(List<Region> regionList) {
        for (Region region : regionList){
            regionDao.saveOrUpdate(region);
        }
    }

    @Override
    public void pageQuery(PageBean pageBean) {
        regionDao.pageQuery(pageBean);
    }

    @Override
    public List<Region> findAll() {
        return regionDao.findById();
    }

    @Override
    public List<Region> findListByQ(String q) {
       return regionDao.findListByQ(q);
    }
}
