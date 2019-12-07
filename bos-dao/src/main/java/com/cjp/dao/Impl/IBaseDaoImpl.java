package com.cjp.dao.Impl;

import com.cjp.dao.IBaseDao;
import com.cjp.domain.Function;
import com.cjp.domain.PageBean;
import com.cjp.domain.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class IBaseDaoImpl<T>  extends HibernateDaoSupport implements IBaseDao<T> {
    private Class<T> entityClass;
    @Override
    public void save(T entity) {
        this.getHibernateTemplate().save(entity);
    }

    @Override
    public void saveOrUpdate(T entity) {
        this.getHibernateTemplate().saveOrUpdate(entity);
    }

    @Resource
    public void setMySessionFactory(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }
    public IBaseDaoImpl(){
        ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
        Type[] actualTypeArguments = genericSuperclass.getActualTypeArguments();
        if (actualTypeArguments != null){
            entityClass = (Class<T>) actualTypeArguments[0];
        }
    }
    @Override
    public void delete(T entity) {
        this.getHibernateTemplate().delete(entity);
    }

    @Override
    public void update(T entity) {
        this.getHibernateTemplate().update(entity);
    }

    @Override
    public T findById(Serializable id) {
        return this.getHibernateTemplate().get(entityClass,id);
    }

    @Override
    public List<T> findById() {
        String hql = "FROM " + entityClass.getSimpleName();
        return (List<T>) this.getHibernateTemplate().find(hql);
    }

    @Override
    public void executeUpdate(Object... objects) {
        Session session = this.getSessionFactory().getCurrentSession();
        Query query = session.createQuery("UPDATE User SET password=? where id = ?");
        int i =0;
        for(Object object : objects){
            query.setParameter(i++,object);
        }
        int i1 = query.executeUpdate();
        System.out.println(i1);
    }

    @Override
    public void executeUpdate(String queryName, Object... objects) {
        Session session = this.getSessionFactory().getCurrentSession();
        Query query = session.getNamedQuery(queryName);
        int i =0;
        for(Object object : objects){
            query.setParameter(i++,object);
        }
        query.executeUpdate();

    }

    @Override
    public void pageQuery(PageBean pageBean) {
        int currPage = pageBean.getCurrPage();
        DetachedCriteria detachedCriteria = pageBean.getDetachedCriteria();
        int pageSize = pageBean.getPageSize();
        detachedCriteria.setProjection(Projections.rowCount());
        List<Long> list = (List<Long>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
        pageBean.setTotal(list.get(0).intValue());
        detachedCriteria.setProjection(null);
        detachedCriteria.setResultTransformer(detachedCriteria.ROOT_ENTITY);
        int first = (currPage-1)*pageSize;
        List rows = this.getHibernateTemplate().findByCriteria(detachedCriteria, first, pageSize);
        pageBean.setRows(rows);
    }

    @Override
    public User findUserByUsername(String username) {
        String hql = "From User Where username = ?";
        List<User> list = (List<User>) getHibernateTemplate().find(hql, username);
        if (list.size() != 0 && list != null){
            return list.get(0);
        }
        return null;
    }
}
