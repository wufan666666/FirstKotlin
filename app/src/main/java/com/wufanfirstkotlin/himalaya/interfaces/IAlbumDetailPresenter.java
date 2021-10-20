package com.wufanfirstkotlin.himalaya.interfaces;

/**
 * @author : wf
 * @date : 2021年10月15日 14:43
 */
public interface IAlbumDetailPresenter {
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

    /**
     * 注册UI通知的接口
     * @param callback
     */
    void registerViewCallback(IAlbumDetailViewCallback callback);

    /**
     * 关闭
     * @param callback
     */
    void unregisterViewCallback(IAlbumDetailViewCallback callback);

}