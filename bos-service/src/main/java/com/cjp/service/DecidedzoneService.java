package com.cjp.service;

import com.cjp.domain.Decidedzone;
import com.cjp.domain.PageBean;

public interface DecidedzoneService {
    void save(Decidedzone model, String... subareaid);

    void pageQuery(PageBean pageBean);
}
