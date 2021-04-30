package com.wufanfirstkotlin.http.okhttp;

/**
 * @author : wf
 * @date : 2021年04月29日 17:05
 */
public class ResponseClass {
    private int code;
    private String protocol;
    private String message;
    private String url;

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}