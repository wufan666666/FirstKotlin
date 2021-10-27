package com.wufanfirstkotlin.himalaya.interfaces;

import com.wufanfirstkotlin.himalaya.base.IBasePresenter;
import com.ximalaya.ting.android.opensdk.player.service.XmPlayListControl;

/**
 * @author : wf
 * @date : 2021年10月27日 14:51
 */
public interface IPlayerPresenter extends IBasePresenter<IPlayerViewCallback> {
    /**
     * 播放
     */
    void play();

    /**
     * 停止播放
     */
    void stop();

    /**
     * 暂停
     */
    void pause();

    /**
     * 播放下一首
     */
    void playNext();

    /**
     * 播放上一首
     */
    void playPre();

    /**
     * 切换播放模式
     * @param mode
     */
    void setPlatMode(XmPlayListControl.PlayMode mode);

    /**
     * 获取播放列表
     */
    void getPlayList();

    /**
     * 根据节目的位置进行播放
     * @param index 节目在列表中的位置
     */
    void playByIndex(int index);

    /**
     * 切换播放进度
     * @param progress
     */
    void seekTo(int progress);
}