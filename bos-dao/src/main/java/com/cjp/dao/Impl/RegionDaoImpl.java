package com.cjp.dao.Impl;

import com.cjp.dao.RegionDao;
import com.cjp.domain.Region;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RegionDaoImpl extends IBaseDaoImpl<Region> implements RegionDao {

    @Override
    public List<Region> findListByQ(String q) {
        List<Region> list = (List<Region>) this.getHibernateTemplate().find("from Region r where r.shortcode like ? or r.citycode like ? or " +
                        " r.province like ? or r.city like ? or r.district like ?", "%" + q + "%", "%" + q + "%", "%" + q + "%", "%" + q + "%"
                , "%" + q + "%");
        return list;
    }
}
