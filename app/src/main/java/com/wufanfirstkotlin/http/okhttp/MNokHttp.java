package com.wufanfirstkotlin.http.okhttp;

/**
 * @author : wf
 * @date : 2021年04月29日 16:31
 */
public class MNokHttp {
    public static<T,M> void sendJsonRequest(T requestData,String url,Class<M> response,
                                            IJsonDataListener listener){
        IHttpRequest httpRequest= new JsonHttpRequest();
        CallbackListener callbackListener= new JsonCallbackListener<>(response,listener);
        HttpTask httpTask= new HttpTask(url,requestData,httpRequest,callbackListener);
        ThreadPollManager.getInstance().addTask(httpTask);

    }
}