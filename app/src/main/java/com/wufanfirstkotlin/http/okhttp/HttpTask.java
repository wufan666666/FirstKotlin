package com.wufanfirstkotlin.http.okhttp;

import com.alibaba.fastjson.JSON;

import java.io.UnsupportedEncodingException;

/**
 * @author : wf
 * @date : 2021年04月29日 16:27
 */
public class HttpTask<T> implements Runnable{

    IHttpRequest httpRequest;

    public HttpTask(String url,T requestData,IHttpRequest httpRequest,CallbackListener listener){
        this.httpRequest=httpRequest;
        httpRequest.setUrl(url);
        httpRequest.setListener(listener);
        try {
            httpRequest.setData(JSON.toJSONString(requestData).getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run() {
        this.httpRequest.execute();
    }
}