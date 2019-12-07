package com.cjp.dao;

import com.cjp.domain.Region;

import java.util.List;

public interface RegionDao extends IBaseDao<Region> {
    List<Region> findListByQ(String q);
}
