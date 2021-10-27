package com.wufanfirstkotlin.himalaya.presenters;

import androidx.annotation.Nullable;

import com.wufanfirstkotlin.himalaya.interfaces.IAlbumDetailPresenter;
import com.wufanfirstkotlin.himalaya.interfaces.IAlbumDetailViewCallback;
import com.wufanfirstkotlin.himalaya.utils.Constants;
import com.wufanfirstkotlin.himalaya.utils.L;
import com.ximalaya.ting.android.opensdk.constants.DTransferConstants;
import com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest;
import com.ximalaya.ting.android.opensdk.datatrasfer.IDataCallBack;
import com.ximalaya.ting.android.opensdk.model.album.Album;
import com.ximalaya.ting.android.opensdk.model.track.Track;
import com.ximalaya.ting.android.opensdk.model.track.TrackList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : wf
 * @date : 2021年10月15日 14:45
 */
public class AlbumDetailPresenter implements IAlbumDetailPresenter {
    private static AlbumDetailPresenter albumDetailPresenter = null;
    private List<IAlbumDetailViewCallback> callbacks = new ArrayList<>();
    private Album targetAlbum = null;
    private String TAG = "AlbumDetailPresenter";

    private AlbumDetailPresenter() {
    }

    public static AlbumDetailPresenter getInstance() {
        if (albumDetailPresenter == null) {
            synchronized (AlbumDetailPresenter.class) {
                if (albumDetailPresenter == null) {
                    albumDetailPresenter = new AlbumDetailPresenter();
                }
            }
        }

        return albumDetailPresenter;
    }


    @Override
    public void pullLoadMore() {

    }

    @Override
    public void refreshList() {

    }

    @Override
    public void getAlbumDetail(int albumId, int page) {
        Map<String, String> map = new HashMap<String, String>();
        onLoadingResult();
        map.put(DTransferConstants.ALBUM_ID, albumId + "");
        map.put(DTransferConstants.SORT, "asc");
        map.put(DTransferConstants.PAGE, page + "");
        map.put(DTransferConstants.PAGE_SIZE, Constants.COUNT_PAGE + "");
        CommonRequest.getTracks(map, new IDataCallBack<TrackList>() {
            @Override
            public void onSuccess(TrackList trackList) {
                if (trackList != null) {
                    List<Track> tracks = trackList.getTracks();
                    L.d(TAG, "tracks size===>" + tracks.size() + "");

                    if (tracks == null || tracks.size() == 0) {
                        onEmptyResult();
                    } else {
                        handleAlbumResult(tracks);
                    }
                }
            }

            @Override
            public void onError(int i, String s) {
                onErrorResult(i,s);
            }
        });
    }

    private void onEmptyResult() {
        for (IAlbumDetailViewCallback callback : callbacks) {
            callback.onEmpty();
        }
    }

    private void onLoadingResult() {
        for (IAlbumDetailViewCallback callback : callbacks) {
            callback.onLoading();
        }
    }

    private void onErrorResult(int errorCode, String errorMsg) {
        for (IAlbumDetailViewCallback callback : callbacks) {
            callback.networkError(errorCode,errorMsg);
        }
    }

    private void handleAlbumResult(List<Track> tracks) {
        for (IAlbumDetailViewCallback callback : callbacks) {
            callback.onDetailListLoad(tracks);
        }
    }

    @Override
    public void registerViewCallback(IAlbumDetailViewCallback callback) {
        if (callback != null && !callbacks.contains(callback)) {
            callbacks.add(callback);
            if (targetAlbum != null) {
                callback.onAlbumLoaded(targetAlbum);
            }
        }
    }

    @Override
    public void unregisterViewCallback(IAlbumDetailViewCallback callback) {
        if (callback != null && callbacks.contains(callback)) {
            callbacks.remove(callback);
        }
    }

    public void setTargetAlbum(Album album) {
        if (album != null) {
            this.targetAlbum = album;
        }
    }
}