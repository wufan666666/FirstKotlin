package com.wufanfirstkotlin.himalaya.fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wufanfirstkotlin.R;
import com.wufanfirstkotlin.himalaya.adapters.RecommendListAdapter;
import com.wufanfirstkotlin.himalaya.base.BaseFragment;
import com.wufanfirstkotlin.himalaya.utils.Constants;
import com.wufanfirstkotlin.himalaya.utils.L;
import com.ximalaya.ting.android.opensdk.constants.DTransferConstants;
import com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest;
import com.ximalaya.ting.android.opensdk.datatrasfer.IDataCallBack;
import com.ximalaya.ting.android.opensdk.model.album.Album;
import com.ximalaya.ting.android.opensdk.model.album.GussLikeAlbumList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : wf
 * @date : 2021年10月09日 15:31
 */
public class ReCommonFragment extends BaseFragment {
    private String TAG = "ReCommonFragment";
    private RecommendListAdapter adapter;

    @Override
    protected View onSubViewLoaded(LayoutInflater layoutInflater, ViewGroup container) {
        View inflate = layoutInflater.inflate(R.layout.fragment_recommend, container,false);
        RecyclerView recyclerView = inflate.findViewById(R.id.recycle_view);
        Context context;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);

        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new RecommendListAdapter();
        recyclerView.setAdapter(adapter);
        getRecommendData();
        return inflate;
    }

    /**
     * 获取推荐内容
     */
    private void getRecommendData() {
        L.d(TAG,"进去了喜马拉雅接口调用");
        Map<String, String> map = new HashMap<>();
        //表示一页显示多少条
        map.put(DTransferConstants.LIKE_COUNT, Constants.RECOMMEND_COUNT+"");
        CommonRequest.getGuessLikeAlbum(map, new IDataCallBack<GussLikeAlbumList>() {
            @Override
            public void onSuccess(GussLikeAlbumList gussLikeAlbumList) {
                if (gussLikeAlbumList!=null){
                    List<Album> albumList = gussLikeAlbumList.getAlbumList();
                    updateRecommendUI(albumList);
                }
            }

            @Override
            public void onError(int i, String s) {
                L.e(TAG,"error====>"+i);
                L.e(TAG,"error====>"+s);
            }
        });
    }

    private void updateRecommendUI(List<Album> albumList) {
        adapter.setData(albumList);
    }
}