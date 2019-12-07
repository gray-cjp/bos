package com.cjp.service;

import com.cjp.domain.PageBean;
import com.cjp.domain.Region;

import java.util.List;

public interface RegionService {

    void saveBatch(List<Region> regionList);

    void pageQuery(PageBean pageBean);

    List<Region> findAll();

    List<Region> findListByQ(String q);
}
