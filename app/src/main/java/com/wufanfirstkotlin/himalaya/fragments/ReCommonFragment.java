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
import com.wufanfirstkotlin.himalaya.interfaces.IRecommendViewCallback;
import com.wufanfirstkotlin.himalaya.presenters.RecommendPresenter;
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
public class ReCommonFragment extends BaseFragment implements IRecommendViewCallback {
    private String TAG = "ReCommonFragment";
    private RecommendListAdapter adapter;
    private RecommendPresenter recommendPresenter;

    @Override
    protected View onSubViewLoaded(LayoutInflater layoutInflater, ViewGroup container) {
        View inflate = layoutInflater.inflate(R.layout.fragment_recommend, container,false);
        RecyclerView recyclerView = inflate.findViewById(R.id.recycle_view);
        Context context;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);

        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new RecommendListAdapter();
        recyclerView.setAdapter(adapter);
        //获取到逻辑层的对象
        recommendPresenter = RecommendPresenter.getInstance();
        //注册callback回调
        recommendPresenter.registerViewCallback(this);
        recommendPresenter.getRecommendList();
        return inflate;
    }





    @Override
    public void getRecommendList(List<Album> result) {
        adapter.setData(result);
    }

    @Override
    public void pullLoadMore(List<Album> result) {

    }

    @Override
    public void refreshList(List<Album> result) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //取消接口的注册，避免内存泄露
        if (recommendPresenter != null) {
            recommendPresenter.unregisterViewCallback(this);
        }
    }
}