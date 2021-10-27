package com.wufanfirstkotlin.himalaya.interfaces;

import com.wufanfirstkotlin.himalaya.base.IBasePresenter;

/**
 * @author : wf
 * @date : 2021年10月15日 14:43
 */
public interface IAlbumDetailPresenter extends IBasePresenter<IAlbumDetailViewCallback> {
    /**
     * 下拉刷新更多内容
     */
    void pullLoadMore();

    /**
     * 上拉刷新数据
     */
    void refreshList();

    /**
     * 获取专辑详情
     * @param albumId
     * @param page
     */
    void getAlbumDetail(int albumId,int page);


}