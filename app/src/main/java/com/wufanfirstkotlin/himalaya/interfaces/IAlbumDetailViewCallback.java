package com.wufanfirstkotlin.himalaya.interfaces;

import com.ximalaya.ting.android.opensdk.model.album.Album;
import com.ximalaya.ting.android.opensdk.model.track.Track;

import java.util.List;

/**
 * @author : wf
 * @date : 2021年10月15日 14:56
 */
public interface IAlbumDetailViewCallback {
    /**
     * 获取专辑详情
     * @param track
     */
    void onDetailListLoad(List<Track> track);

    /**
     * 加载专辑信息
     * @param album
     */
    void onAlbumLoaded(Album album);
    /**
     * 网络错误
     * @param errorCode
     * @param errorMsg
     */
    void networkError(int errorCode, String errorMsg);

    /**
     * 数据为空
     */
    void onEmpty();

    /**
     * 正在加载
     */
    void onLoading();
}