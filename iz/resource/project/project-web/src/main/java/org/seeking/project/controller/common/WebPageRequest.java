package org.seeking.{{ project }}.controller.common;


public abstract class WebPageRequest {
    protected int pageindex = 1;

    protected int pagesize = 10;

    public int getPagesize() {
        return pagesize;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }

    public int getPageindex() {
        return pageindex;
    }

    public void setPageindex(int pageindex) {
        this.pageindex = pageindex;
    }
}
