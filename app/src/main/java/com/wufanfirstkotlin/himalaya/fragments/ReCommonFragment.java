package com.wufanfirstkotlin.himalaya.fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.wufanfirstkotlin.R;
import com.wufanfirstkotlin.himalaya.adapters.RecommendListAdapter;
import com.wufanfirstkotlin.himalaya.base.BaseFragment;
import com.wufanfirstkotlin.himalaya.interfaces.IRecommendViewCallback;
import com.wufanfirstkotlin.himalaya.presenters.RecommendPresenter;
import com.wufanfirstkotlin.himalaya.utils.Constants;
import com.wufanfirstkotlin.himalaya.utils.L;
import com.wufanfirstkotlin.himalaya.views.UILoader;
import com.ximalaya.ting.android.opensdk.constants.DTransferConstants;
import com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest;
import com.ximalaya.ting.android.opensdk.datatrasfer.IDataCallBack;
import com.ximalaya.ting.android.opensdk.model.album.Album;
import com.ximalaya.ting.android.opensdk.model.album.GussLikeAlbumList;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : wf
 * @date : 2021年10月09日 15:31
 */
public class ReCommonFragment extends BaseFragment implements IRecommendViewCallback, UILoader.OnRetryClickListener, OnRefreshListener, OnLoadMoreListener {
    private String TAG = "ReCommonFragment";
    private RecommendListAdapter adapter;
    private RecommendPresenter recommendPresenter;
    private UILoader uiLoader;

    @Override
    protected View onSubViewLoaded(LayoutInflater layoutInflater, ViewGroup container) {
        uiLoader = new UILoader(getContext()) {
            @Override
            protected View getSuccessView(ViewGroup container) {
                return onSuccessView(layoutInflater,container);
            }
        };

        //获取到逻辑层的对象
        recommendPresenter = RecommendPresenter.getInstance();
        //注册callback回调
        recommendPresenter.registerViewCallback(this);
        recommendPresenter.getRecommendList();
        if (uiLoader.getParent() instanceof ViewGroup){
            ((ViewGroup) uiLoader.getParent()).removeView(uiLoader);
        }
        uiLoader.setOnRetryClickListener(this);
        return uiLoader;
    }

    private View onSuccessView(LayoutInflater layoutInflater, ViewGroup container) {
        View inflate = layoutInflater.inflate(R.layout.fragment_recommend, container,false);
        RecyclerView recyclerView = inflate.findViewById(R.id.recycle_view);
        SmartRefreshLayout refreshLayout = inflate.findViewById(R.id.refresh_layout);
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setOnLoadMoreListener(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);

        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new RecommendListAdapter();
        recyclerView.setAdapter(adapter);
        return inflate;
    }


    @Override
    public void getRecommendList(List<Album> result) {
        adapter.setData(result);
        uiLoader.updateStatus(UILoader.UIStatus.SUCCESS);
    }

    @Override
    public void networkError() {
        uiLoader.updateStatus(UILoader.UIStatus.NETWORK_ERROR);
    }

    @Override
    public void onEmpty() {
        uiLoader.updateStatus(UILoader.UIStatus.NONE);
    }

    @Override
    public void onLoading() {
        uiLoader.updateStatus(UILoader.UIStatus.LOADING);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //取消接口的注册，避免内存泄露
        if (recommendPresenter != null) {
            recommendPresenter.unregisterViewCallback(this);
        }
    }

    @Override
    public void onRetry() {
        if (recommendPresenter!=null){
            recommendPresenter.getRecommendList();
        }
    }

    @Override
    public void onRefresh(@NonNull @NotNull RefreshLayout refreshLayout) {
        refreshLayout.finishRefresh(100);
        recommendPresenter.refreshList();
    }

    @Override
    public void onLoadMore(@NonNull @NotNull RefreshLayout refreshLayout) {
        refreshLayout.finishLoadMore(100);
        recommendPresenter.pullLoadMore();
    }
}