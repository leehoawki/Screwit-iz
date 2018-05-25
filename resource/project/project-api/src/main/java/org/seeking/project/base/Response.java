package org.seeking.{{ project }}.base;

public class Response<T> {
    private T data;

    private int code;

    private PageInfo pageInfo ;

    public static <T> Response<T> success(T data) {
        Response<T> response = new Response<>();
        response.setCode(2000);
        response.setData(data);
        return response;
    }

    public static <T> Response<T> success(T data, PageInfo pageInfo) {
        Response<T> response = new Response<>();
        response.setCode(2000);
        response.setData(data);
        response.setPageInfo(pageInfo);
        return response;
    }

    public static Response<String> error(int code, String message) {
        Response response = new Response();
        response.setCode(code);
        response.setData(message);
        return response;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }
}