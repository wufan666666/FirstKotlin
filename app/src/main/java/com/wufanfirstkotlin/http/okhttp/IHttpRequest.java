package com.wufanfirstkotlin.http.okhttp;

import javax.security.auth.callback.Callback;

/**
 * @author : wf
 * @date : 2021年04月29日 15:09
 */
public interface IHttpRequest {

    void setUrl(String URL);
    void setData(byte[] data);
    void setListener(CallbackListener listener);
    void execute();
}