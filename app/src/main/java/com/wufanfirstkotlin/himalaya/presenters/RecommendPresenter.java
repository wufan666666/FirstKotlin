package com.wufanfirstkotlin.himalaya.presenters;

import com.wufanfirstkotlin.himalaya.interfaces.IRecommendPresenter;
import com.wufanfirstkotlin.himalaya.interfaces.IRecommendViewCallback;
import com.wufanfirstkotlin.himalaya.utils.Constants;
import com.wufanfirstkotlin.himalaya.utils.L;
import com.ximalaya.ting.android.opensdk.constants.DTransferConstants;
import com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest;
import com.ximalaya.ting.android.opensdk.datatrasfer.IDataCallBack;
import com.ximalaya.ting.android.opensdk.model.album.Album;
import com.ximalaya.ting.android.opensdk.model.album.GussLikeAlbumList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : wf
 * @date : 2021年10月11日 15:44
 */
public class RecommendPresenter implements IRecommendPresenter {

    private static RecommendPresenter recommendPresenter = null;
    private String TAG = "RecommendPresenter";
    private List<IRecommendViewCallback> mCallBack = new ArrayList<>();
    private List<Album> albumArrayList = new ArrayList<>();
    private boolean isDropDown;

    private RecommendPresenter() {
    }

    public static RecommendPresenter getInstance() {
        if (recommendPresenter == null) {
            synchronized (RecommendPresenter.class) {
                if (recommendPresenter == null) {
                    recommendPresenter = new RecommendPresenter();
                }
            }
        }
        return recommendPresenter;
    }

    @Override
    public void getRecommendList() {
        getRecommendData();
    }

    @Override
    public void pullLoadMore() {
        isDropDown = true;
        getRecommendData();
    }

    @Override
    public void refreshList() {
        getRecommendData();
    }


    @Override
    public void registerViewCallback(IRecommendViewCallback callback) {
        if (mCallBack != null && !mCallBack.contains(callback)) {
            mCallBack.add(callback);
        }
    }

    @Override
    public void unregisterViewCallback(IRecommendViewCallback callback) {
        if (mCallBack != null && mCallBack.contains(callback)) {
            mCallBack.remove(callback);
        }
    }

    /**
     * 获取推荐内容
     */
    private void getRecommendData() {
        Map<String, String> map = new HashMap<>();
        onLoading();
        //表示一页显示多少条
        map.put(DTransferConstants.LIKE_COUNT, Constants.RECOMMEND_COUNT + "");
        CommonRequest.getGuessLikeAlbum(map, new IDataCallBack<GussLikeAlbumList>() {
            @Override
            public void onSuccess(GussLikeAlbumList gussLikeAlbumList) {
                if (gussLikeAlbumList != null) {
                    List<Album> albumList = gussLikeAlbumList.getAlbumList();
                    if (isDropDown) {
                        albumArrayList.addAll(albumList);
                        isDropDown = false;
                        updateRecommendUI(albumArrayList);
                    } else {
                        updateRecommendUI(albumList);
                    }
                }
            }

            @Override
            public void onError(int i, String s) {
                L.e(TAG, "error====>" + i);
                L.e(TAG, "error====>" + s);
                handleError();
            }
        });
    }

    private void handleError() {
        if (mCallBack != null) {
            for (IRecommendViewCallback iRecommendViewCallback : mCallBack) {
                iRecommendViewCallback.networkError();
            }
        }
    }

    void onLoading() {
        if (mCallBack != null) {
            for (IRecommendViewCallback iRecommendViewCallback : mCallBack) {
                iRecommendViewCallback.onLoading();
            }
        }
    }

    private void updateRecommendUI(List<Album> albumList) {
        if (mCallBack != null) {
            if (albumList.size() == 0) {
                for (IRecommendViewCallback iRecommendViewCallback : mCallBack) {
                    iRecommendViewCallback.onEmpty();
                }
            } else {

                for (IRecommendViewCallback iRecommendViewCallback : mCallBack) {
                    iRecommendViewCallback.getRecommendList(albumList);
                }
            }
        }
    }

}