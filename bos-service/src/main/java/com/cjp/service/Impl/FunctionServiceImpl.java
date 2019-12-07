package com.cjp.service.Impl;

import com.cjp.dao.FunctionDao;
import com.cjp.domain.Function;
import com.cjp.service.FunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FunctionServiceImpl implements FunctionService {
    @Autowired
    private FunctionDao functionDao;

    @Override
    public List<Function> findAll() {
        return functionDao.findById();
    }
}
