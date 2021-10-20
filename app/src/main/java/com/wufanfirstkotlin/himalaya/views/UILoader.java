package com.wufanfirstkotlin.himalaya.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.wufanfirstkotlin.MyApplication;
import com.wufanfirstkotlin.R;


/**
 * @author : wf
 * @date : 2021年10月14日 9:55
 */
public abstract class UILoader extends FrameLayout {

    private View loadingView;
    private View successView;
    private View networkErrorView;
    private View emptyView;

    public enum UIStatus {
        LOADING, SUCCESS, NETWORK_ERROR, NONE
    }

    public UIStatus mCurrentStatus = UIStatus.NONE;

    public UILoader(@NonNull Context context) {
        this(context, null);
    }

    public UILoader(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public UILoader(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initUI();
    }

    /**
     * 初始化UI
     */
    private void initUI() {
        switchUIByCurrentStatus();
    }

    public void updateStatus(UIStatus status) {
        mCurrentStatus = status;
        MyApplication.Companion.getMyHandler().post(new Runnable() {
            @Override
            public void run() {
                switchUIByCurrentStatus();
            }
        });
    }

    private void switchUIByCurrentStatus() {
        //加载中
        if (loadingView == null) {
            loadingView = getLoadingView();
            addView(loadingView);
        }
        //根据状态设置判断是否可见
        loadingView.setVisibility(mCurrentStatus == UIStatus.LOADING ? VISIBLE : GONE);

        //成功
        if (successView == null) {
            successView = getSuccessView(this);
            addView(successView);
        }
        //根据状态设置判断是否可见
        successView.setVisibility(mCurrentStatus == UIStatus.SUCCESS ? VISIBLE : GONE);

        //网络错误
        if (networkErrorView == null) {
            networkErrorView = getNetworkErrorView();
            addView(networkErrorView);
        }
        //根据状态设置判断是否可见
        networkErrorView.setVisibility(mCurrentStatus == UIStatus.NETWORK_ERROR ? VISIBLE : GONE);

        //空网页
        if (emptyView == null) {
            emptyView = getEmptyView();
            addView(emptyView);
        }
        //根据状态设置判断是否可见
        emptyView.setVisibility(mCurrentStatus == UIStatus.NONE ? VISIBLE : GONE);
    }

    /**
     * @return
     */
    protected View getEmptyView() {
        return LayoutInflater.from(getContext()).inflate(R.layout.fragment_empty_view, this, false);
    }

    /**
     * @return
     */
    protected View getNetworkErrorView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_network_error, this, false);
        RelativeLayout container = view.findViewById(R.id.content_container);
        container.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (retryClickListener != null) {
                    retryClickListener.onRetry();
                }
            }
        });
        return view;
    }

    /**
     * @return
     */
    protected abstract View getSuccessView(ViewGroup container);

    private View getLoadingView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_onloading, this, false);
        //ImageView imageView = view.findViewById(R.id.loading_image_view);
        //Glide.with(this).load(R.drawable.loading).into(imageView);
        return view;
    }

    public OnRetryClickListener retryClickListener;

    public void setOnRetryClickListener(OnRetryClickListener onRetryClickListener) {
        this.retryClickListener = onRetryClickListener;
    }

    public interface OnRetryClickListener {
        void onRetry();
    }
}