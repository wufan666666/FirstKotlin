package com.wufanfirstkotlin.himalaya.base;

/**
 * @author : wf
 * @date : 2021年10月27日 14:44
 */
public interface IBasePresenter<T> {
    /**
     * 注册UI通知的接口
     * @param t
     */
    void registerViewCallback(T t);

    /**
     * 关闭
     * @param t
     */
    void unregisterViewCallback(T t);
}