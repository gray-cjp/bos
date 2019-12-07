package com.cjp.dao;

import com.cjp.domain.PageBean;
import com.cjp.domain.User;

import java.io.Serializable;
import java.util.List;

public interface IBaseDao<T> {
    public void save(T entity);
    public void saveOrUpdate(T entity);
    public void delete(T entity);
    public void update(T entity);
    public T findById(Serializable id);
    public List<T> findById();
    public void executeUpdate(Object... objects);
    public void executeUpdate(String queryName,Object... objects);
    public void pageQuery(PageBean pageBean);
    User findUserByUsername(String username);
}
