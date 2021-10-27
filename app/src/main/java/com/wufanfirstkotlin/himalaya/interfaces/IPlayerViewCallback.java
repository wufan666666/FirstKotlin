package com.wufanfirstkotlin.himalaya.interfaces;

import android.os.Trace;

import com.ximalaya.ting.android.opensdk.player.service.XmPlayListControl;

import java.util.List;

/**
 * @author : wf
 * @date : 2021年10月27日 14:51
 */
public interface IPlayerViewCallback {
    /**
     * 播放开始
     */
    void onPlayStart();

    /**
     * 播放停止
     */
    void onPlayStop();

    /**
     * 播放暂停
     */
    void onPlayPause();

    /**
     * 播放错误
     */
    void onPlayError();

    /**
     * 播放下一首
     * @param trace
     */
    void nextPlay(Trace trace);

    /**
     * 播放上一首
     * @param trace
     */
    void prePlay(Trace trace);

    /**
     * 播放列表数据加载
     * @param list 播放器列表数据
     */
    void onListLoaded(List<Trace> list);

    /**
     * 切换播放器播放模式
     * @param playMode
     */
    void onPlayModeChange(XmPlayListControl.PlayMode playMode);

    /**
     * 进度条的改变
     * @param currentProgress
     * @param total
     */
    void onProgressChange(long currentProgress,long total);

    /**
     * 广告正在加载
     */
    void onAdLoading();

    /**
     * 广告加载结束
     */
    void onAdLoaded();
}