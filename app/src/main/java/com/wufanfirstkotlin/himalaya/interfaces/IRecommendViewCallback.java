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
     *
     * @param result
     */
    void getRecommendList(List<Album> result);

    /**
     * 网络错误
     */
    void networkError();

    /**
     * 数据为空
     */
    void onEmpty();

    /**
     * 正在加载
     */
    void onLoading();
}