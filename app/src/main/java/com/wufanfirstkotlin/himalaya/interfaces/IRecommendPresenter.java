package com.wufanfirstkotlin.himalaya.interfaces;

/**
 * @author : wf
 * @date : 2021年10月11日 15:01
 */
public interface IRecommendPresenter {
    /**
     * 获取推荐内容
     */
    void getRecommendList();

    /**
     * 下拉刷新更多内容
     */
    void pullLoadMore();

    /**
     * 上拉刷新数据
     */
    void refreshList();

    /**
     * 注册
     * @param callback
     */
    void registerViewCallback(IRecommendViewCallback callback);

    /**
     * 关闭
     * @param callback
     */
    void unregisterViewCallback(IRecommendViewCallback callback);


}