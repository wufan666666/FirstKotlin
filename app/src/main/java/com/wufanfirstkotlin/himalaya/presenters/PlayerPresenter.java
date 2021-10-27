package com.wufanfirstkotlin.himalaya.presenters;

import android.os.Trace;

import com.wufanfirstkotlin.MyApplication;
import com.wufanfirstkotlin.himalaya.interfaces.IPlayerPresenter;
import com.wufanfirstkotlin.himalaya.interfaces.IPlayerViewCallback;
import com.wufanfirstkotlin.himalaya.utils.L;
import com.ximalaya.ting.android.opensdk.model.track.Track;
import com.ximalaya.ting.android.opensdk.player.XmPlayerManager;
import com.ximalaya.ting.android.opensdk.player.service.XmPlayListControl;

import java.util.List;

/**
 * @author : wf
 * @date : 2021年10月27日 15:31
 */
public class PlayerPresenter implements IPlayerPresenter {

    private static PlayerPresenter playerPresenter = null;
    private final XmPlayerManager mXmPlayerManager;
    private String TAG  = "PlayerPresenter";
    private boolean isPlaySet = false;

    private PlayerPresenter() {
        mXmPlayerManager = XmPlayerManager.getInstance(MyApplication.Companion.getAppContext());

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

    }

    @Override
    public void playNext() {

    }

    @Override
    public void playPre() {

    }

    @Override
    public void setPlatMode(XmPlayListControl.PlayMode mode) {

    }

    @Override
    public void getPlayList() {

    }

    @Override
    public void playByIndex(int index) {

    }

    @Override
    public void seekTo(int progress) {

    }

    @Override
    public void registerViewCallback(IPlayerViewCallback iPlayerViewCallback) {

    }

    @Override
    public void unregisterViewCallback(IPlayerViewCallback iPlayerViewCallback) {

    }
}