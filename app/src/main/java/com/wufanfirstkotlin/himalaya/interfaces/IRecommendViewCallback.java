package com.wufanfirstkotlin.himalaya.interfaces;

import com.ximalaya.ting.android.opensdk.model.album.Album;

import java.util.List;

/**
 * @author : wf
 * @date : 2021年10月11日 15:41
 */
public interface IRecommendViewCallback {
    /**
     * 获取推荐结果
     * @param result
     */
    void getRecommendList(List<Album> result);

    /**
     * 获取下拉推荐结果
     * @param result
     */
    void pullLoadMore(List<Album> result);

    /**
     * 获取上拉推荐结果
     *
     * @param result
     */
    void refreshList(List<Album> result);
}