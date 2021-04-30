package com.wufanfirstkotlin.http.okhttp;

/**
 * @author : wf
 * @date : 2021年04月29日 16:22
 */
public interface IJsonDataListener<T> {
    void onSuccess(T m);
}