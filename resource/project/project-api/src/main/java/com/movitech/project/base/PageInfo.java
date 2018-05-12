package com.movitech.{{ project }}.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageInfo {
    private int pagesize = 10;

    private int pagenum = 0;

    private long total;

    private String sortby = "id";

    private int order = 0;

    public PageInfo() {

    }

    public PageInfo(Page page) {
        this.pagesize = page.getSize();
        this.pagenum = page.getNumber();
        this.total = page.getTotalElements();
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getSortBy() {
        return sortby;
    }

    public void setSortBy(String sortBy) {
        this.sortby = sortBy;
    }

    public int getPageNum() {
        return pagenum;
    }

    public void setPageNum(int pageNum) {
        this.pagenum = pageNum;
    }

    public int getPageSize() {
        return pagesize;
    }

    public void setPageSize(int pageSize) {
        this.pagesize = pageSize;
    }

    public Pageable pageable() {
        Sort sort;
        if (order > 0) sort = new Sort(Sort.Direction.ASC, sortby);
        else sort = new Sort(Sort.Direction.DESC, sortby);
        PageRequest pageRequest = new PageRequest(pagenum, pagesize, sort);
        return pageRequest;
    }

    @Override
    public String toString() {
        return "PageInfo{" +
                "pagesize=" + pagesize +
                ", pagenum=" + pagenum +
                ", total=" + total +
                ", sortby='" + sortby + '\'' +
                ", order=" + order +
                '}';
    }
}