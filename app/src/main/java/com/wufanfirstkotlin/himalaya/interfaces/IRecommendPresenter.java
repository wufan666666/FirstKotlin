package com.wufanfirstkotlin.himalaya.interfaces;

import com.wufanfirstkotlin.himalaya.base.IBasePresenter;

/**
 * @author : wf
 * @date : 2021年10月11日 15:01
 */
public interface IRecommendPresenter extends IBasePresenter<IRecommendViewCallback> {
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



}