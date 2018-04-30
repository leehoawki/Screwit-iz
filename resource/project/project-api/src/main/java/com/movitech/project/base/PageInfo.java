package com.movitech.{{ project }}.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageInfo {
    private int pageSize = 10;

    private int pageNum = 0;

    private long total;

    private String sortBy;

    private int order;

    public PageInfo() {

    }

    public PageInfo(Page page) {
        this.pageSize = page.getSize();
        this.pageNum = page.getNumber();
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
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Pageable pageable() {
        Sort sort;
        if (order > 0) sort = new Sort(Sort.Direction.ASC, sortBy);
        else sort = new Sort(Sort.Direction.DESC, sortBy);
        PageRequest pageRequest = new PageRequest(pageNum, pageSize, sort);
        return pageRequest;
    }
}