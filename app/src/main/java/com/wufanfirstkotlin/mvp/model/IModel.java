package com.wufanfirstkotlin.mvp.model;

/**
 * @author : wf
 * @date : 2021年06月08日 16:58
 */
public interface IModel {

    void login(String username,String password,Callback callback);
}