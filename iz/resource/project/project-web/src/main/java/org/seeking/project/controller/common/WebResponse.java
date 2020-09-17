package org.seeking.{{ project }}.controller.common;

public class WebResponse<T> {
    T data;

    int error;

    String message;

    public static <T> WebResponse<T> create(T result) {
        WebResponse<T> webResponse = new WebResponse();
        webResponse.setData(result);
        webResponse.setMessage("success");
        return webResponse;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "WebResponse{" +
                "data=" + data +
                ", error=" + error +
                ", message='" + message + '\'' +
                '}';
    }
}

