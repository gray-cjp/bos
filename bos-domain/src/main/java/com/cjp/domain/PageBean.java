package com.cjp.domain;

import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

public class PageBean {
    //当前页
    private int currPage;
    //每页显示条数
    private int pageSize;
    //总条数
    private int total;
    //分页查询离线条件
    private DetachedCriteria detachedCriteria;
    //显示数据集合
    private List rows;

    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public DetachedCriteria getDetachedCriteria() {
        return detachedCriteria;
    }

    public void setDetachedCriteria(DetachedCriteria detachedCriteria) {
        this.detachedCriteria = detachedCriteria;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }
}
