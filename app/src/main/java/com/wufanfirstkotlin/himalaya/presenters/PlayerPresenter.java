package com.wufanfirstkotlin.himalaya.presenters;

import android.os.Trace;

import com.wufanfirstkotlin.MyApplication;
import com.wufanfirstkotlin.himalaya.interfaces.IPlayerPresenter;
import com.wufanfirstkotlin.himalaya.interfaces.IPlayerViewCallback;
import com.wufanfirstkotlin.himalaya.utils.L;
import com.ximalaya.ting.android.opensdk.model.PlayableModel;
import com.ximalaya.ting.android.opensdk.model.advertis.Advertis;
import com.ximalaya.ting.android.opensdk.model.advertis.AdvertisList;
import com.ximalaya.ting.android.opensdk.model.track.Track;
import com.ximalaya.ting.android.opensdk.player.XmPlayerManager;
import com.ximalaya.ting.android.opensdk.player.advertis.IXmAdsStatusListener;
import com.ximalaya.ting.android.opensdk.player.service.IXmPlayerStatusListener;
import com.ximalaya.ting.android.opensdk.player.service.XmPlayListControl;
import com.ximalaya.ting.android.opensdk.player.service.XmPlayerException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : wf
 * @date : 2021年10月27日 15:31
 */
public class PlayerPresenter implements IPlayerPresenter, IXmAdsStatusListener, IXmPlayerStatusListener {

    private static PlayerPresenter playerPresenter = null;
    private final XmPlayerManager mXmPlayerManager;
    private String TAG  = "PlayerPresenter";
    private boolean isPlaySet = false;
    private List<IPlayerViewCallback> mCallbacks = new ArrayList<>();
    private Track mTrackUpdate;

    private PlayerPresenter() {
        mXmPlayerManager = XmPlayerManager.getInstance(MyApplication.Companion.getAppContext());
        //注册广告物料相关的接口
        mXmPlayerManager.addAdsStatusListener(this);
        //注册播放器状态相关的接口
        mXmPlayerManager.addPlayerStatusListener(this);
    }

    public static PlayerPresenter getInstance() {
        if (playerPresenter == null) {
            synchronized (PlayerPresenter.class) {
                if (playerPresenter == null) {
                    playerPresenter = new PlayerPresenter();
                }
            }
        }
        return playerPresenter;
    }

    public void setPlayList(List<Track> tracks, int playIndex){
        if (mXmPlayerManager != null) {
            mXmPlayerManager.setPlayList(tracks,playIndex);
            isPlaySet = true;
            mTrackUpdate = tracks.get(playIndex);
        }else {
            L.d(TAG,"mXmPlayerManager is null");
        }
    }
    @Override
    public void play() {
        if (isPlaySet) {
            L.e(TAG,"该有声了");
            mXmPlayerManager.play();
        }
    }

    @Override
    public void stop() {

    }

    @Override
    public void pause() {
        if (mXmPlayerManager != null) {
            mXmPlayerManager.pause();
        }
    }

    @Override
    public void playNext() {
        if (mXmPlayerManager != null) {
            mXmPlayerManager.playNext();
        }
    }

    @Override
    public void playPre() {
        if (mXmPlayerManager != null) {
            mXmPlayerManager.playPre();
        }
    }

    @Override
    public void setPlatMode(XmPlayListControl.PlayMode mode) {

    }

    @Override
    public void getPlayList() {
        if (mXmPlayerManager != null) {
            List<Track> playList = mXmPlayerManager.getPlayList();

            for (IPlayerViewCallback callback : mCallbacks) {
                callback.onListLoaded(playList);
            }
        }
    }

    @Override
    public void playByIndex(int index) {
        if (mXmPlayerManager != null) {
            mXmPlayerManager.play(index);
        }
    }

    @Override
    public void seekTo(int progress) {
        mXmPlayerManager.seekTo(progress);
    }

    @Override
    public boolean isPlay() {
        return  mXmPlayerManager.isPlaying();
    }

    @Override
    public void registerViewCallback(IPlayerViewCallback iPlayerViewCallback) {
        iPlayerViewCallback.onUpdateTrack(mTrackUpdate);
        if (!mCallbacks.contains(iPlayerViewCallback)) {
            mCallbacks.add(iPlayerViewCallback);
        }
    }

    @Override
    public void unregisterViewCallback(IPlayerViewCallback iPlayerViewCallback) {
        mCallbacks.remove(iPlayerViewCallback);
    }
    //========================广告相关的回调方法 start===============================

    @Override
    public void onStartGetAdsInfo() {
        L.d(TAG,"onStartGetAdsInfo...");
    }

    @Override
    public void onGetAdsInfo(AdvertisList advertisList) {
        L.d(TAG,"onGetAdsInfo...");
    }

    @Override
    public void onAdsStartBuffering() {
        L.d(TAG,"onAdsStartBuffering...");
    }

    @Override
    public void onAdsStopBuffering() {
        L.d(TAG,"onAdsStopBuffering...");
    }

    @Override
    public void onStartPlayAds(Advertis advertis, int i) {
        L.d(TAG,"onStartPlayAds...");
    }

    @Override
    public void onCompletePlayAds() {
        L.d(TAG,"onCompletePlayAds...");
    }

    @Override
    public void onError(int i, int i1) {
        L.d(TAG,"onError...");
    }
    //========================广告相关的回调方法 end===============================
    //
    //=======================播放器状态相关的回调 start============================

    @Override
    public void onPlayStart() {
        L.d(TAG,"onPlayStart...");
        for (IPlayerViewCallback callback : mCallbacks) {
            callback.onPlayStart();
        }
    }

    @Override
    public void onPlayPause() {
        L.d(TAG,"onPlayPause...");
        for (IPlayerViewCallback callback : mCallbacks) {
            callback.onPlayPause();
        }
    }

    @Override
    public void onPlayStop() {
        L.d(TAG,"onPlayStop...");
        for (IPlayerViewCallback callback : mCallbacks) {
            callback.onPlayStop();
        }
    }

    @Override
    public void onSoundPlayComplete() {
        L.d(TAG,"onSoundPlayComplete...");
    }

    @Override
    public void onSoundPrepared() {
        L.d(TAG,"onSoundPrepared...");
    }

    @Override
    public void onSoundSwitch(PlayableModel lastModel, PlayableModel curModel) {
        L.d(TAG,"onSoundSwitch...");

        if (curModel instanceof Track) {
            for (IPlayerViewCallback callback : mCallbacks) {
                callback.onUpdateTrack((Track) curModel);
            }
        }
    }

    @Override
    public void onBufferingStart() {
        L.d(TAG,"onBufferingStart...");
    }

    @Override
    public void onBufferingStop() {
        L.d(TAG,"onBufferingStop...");
    }

    @Override
    public void onBufferProgress(int progress) {
        L.d(TAG,"onBufferProgress...");
    }

    @Override
    public void onPlayProgress(int curPos, int duration) {
        for (IPlayerViewCallback callback : mCallbacks) {
            callback.onProgressChange(curPos,duration);
        }
    }

    @Override
    public boolean onError(XmPlayerException e) {
        L.d(TAG,"onError..."+e);
        return false;
    }
    //=======================播放器状态相关的回调 end===========================



}