package org.seeking.{{ project }}.controller.common;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.List;

public class WebPagedList<T> {
    List<T> sourceData;

    int pageIndex;

    int pageSize;

    int totalCount;

    int totalPages;

    public static <T> WebPagedList<T> create(List<T> sourceData, int pageIndex, int pageSize, int totalCount) {
        WebPagedList<T> webPagedList = new WebPagedList();
        webPagedList.setSourceData(sourceData);
        webPagedList.setPageIndex(pageIndex);
        webPagedList.setPageSize(pageSize);
        webPagedList.setTotalCount(totalCount);

        int totalPages = totalCount / pageSize;
        if (totalCount % pageSize > 0) totalPages += 1;
        webPagedList.setTotalPages(totalPages);

        return webPagedList;
    }

    public static <T> WebPagedList<T> empty(int pageIndex, int pageSize) {
        WebPagedList<T> webPagedList = new WebPagedList();
        webPagedList.setSourceData(Arrays.asList());
        webPagedList.setPageIndex(pageIndex);
        webPagedList.setPageSize(pageSize);
        webPagedList.setTotalCount(0);
        webPagedList.setTotalPages(0);
        return webPagedList;
    }

    public List<T> getSourceData() {
        return sourceData;
    }

    public void setSourceData(List<T> sourceData) {
        this.sourceData = sourceData;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public void setHasPreviousPage(boolean hasPreviousPage){
        //
    }

    @JsonProperty("hasPreviousPage")
    public boolean hasPreviousPage() {
        return this.pageIndex > 1;
    }

    public void setHasNextPage(boolean hasNextPage){
        //
    }

    @JsonProperty("hasNextPage")
    public boolean hasNextPage() {
        return this.pageIndex < this.totalPages;
    }

    @Override
    public String toString() {
        return "WebPagedList{" +
                "sourceData=" + sourceData +
                ", pageIndex=" + pageIndex +
                ", pageSize=" + pageSize +
                ", totalCount=" + totalCount +
                ", totalPages=" + totalPages +
                '}';
    }
}
