package com.wufanfirstkotlin.mvp.model;

/**
 * @author : wf
 * @date : 2021年06月08日 16:41
 */
public interface Callback {

    void onSuccess(User user);

    void onFailure(String msg);
}