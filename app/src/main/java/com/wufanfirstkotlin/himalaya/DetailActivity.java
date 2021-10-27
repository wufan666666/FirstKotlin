package com.wufanfirstkotlin.himalaya;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.wufanfirstkotlin.BaseActivity;
import com.wufanfirstkotlin.R;
import com.wufanfirstkotlin.himalaya.adapters.DetailListAdapter;
import com.wufanfirstkotlin.himalaya.interfaces.IAlbumDetailViewCallback;
import com.wufanfirstkotlin.himalaya.presenters.AlbumDetailPresenter;
import com.wufanfirstkotlin.himalaya.presenters.PlayerPresenter;
import com.wufanfirstkotlin.himalaya.utils.ImageBlur;
import com.wufanfirstkotlin.himalaya.utils.L;
import com.wufanfirstkotlin.himalaya.views.CustomRoundAngleImageView;
import com.wufanfirstkotlin.himalaya.views.UILoader;
import com.ximalaya.ting.android.opensdk.model.album.Album;
import com.ximalaya.ting.android.opensdk.model.track.Track;

import java.util.List;

import static com.ximalaya.ting.android.opensdk.util.Utils.getContext;

/**
 * @author wf
 */
public class DetailActivity extends BaseActivity implements IAlbumDetailViewCallback, UILoader.OnRetryClickListener, DetailListAdapter.OnDetailItemClickListener {

    private ImageView coverBg;
    private CustomRoundAngleImageView smallCover;
    private TextView tvAlbumTitle;
    private TextView tvAlbumAuthor;
    private String TAG = "DetailActivity";
    private AlbumDetailPresenter albumDetailPresenter;
    private int current_page = 1;
    private RelativeLayout subscriptionContainer;
    private TextView subscribeButton;
    private RelativeLayout playContainer;
    private ImageView playImage;
    private TextView playTv;
    private TextView selectCountTv;
    private ImageView selectCountImage;
    private RecyclerView playRecycleView;
    private DetailListAdapter detailListAdapter;
    private FrameLayout frameLayoutRecycle;
    private UILoader uiLoader;
    private int albumId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initView();
        //沉浸式状态栏+毛玻璃效果
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.INVISIBLE);
        albumDetailPresenter = AlbumDetailPresenter.getInstance();
        albumDetailPresenter.registerViewCallback(this);
    }

    private void initView() {
        frameLayoutRecycle = findViewById(R.id.framelayout_recycle);
        if (uiLoader == null) {
            uiLoader = new UILoader(this) {
                @Override
                protected View getSuccessView(ViewGroup container) {
                    return onSuccessView(container);
                }
            };

        }
        frameLayoutRecycle.removeAllViews();
        frameLayoutRecycle.addView(uiLoader);

        coverBg = findViewById(R.id.cover_bg);
        smallCover = findViewById(R.id.small_cover);
        tvAlbumTitle = findViewById(R.id.tv_album_title);
        tvAlbumAuthor = findViewById(R.id.tv_album_author);
        subscriptionContainer = (RelativeLayout) findViewById(R.id.subscription_container);
        subscribeButton = (TextView) findViewById(R.id.subscribe_button);
        playContainer = (RelativeLayout) findViewById(R.id.play_container);
        playImage = (ImageView) findViewById(R.id.play_image);
        playTv = (TextView) findViewById(R.id.play_tv);
        selectCountTv = (TextView) findViewById(R.id.select_count_tv);
        selectCountImage = (ImageView) findViewById(R.id.select_count_image);
        uiLoader.setOnRetryClickListener(this);


    }

    private View onSuccessView(ViewGroup container) {
        View successView = LayoutInflater.from(DetailActivity.this).inflate(R.layout.item_detail_recycle, container, false);
        playRecycleView = successView.findViewById(R.id.play_recycle_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        detailListAdapter = new DetailListAdapter();
        playRecycleView.setLayoutManager(linearLayoutManager);
        playRecycleView.setAdapter(detailListAdapter);
        detailListAdapter.setOnItemClickListener(this);
        return successView;
    }

    @Override
    public void onDetailListLoad(List<Track> track) {
        if (track.size() == 0 || track == null) {
            uiLoader.updateStatus(UILoader.UIStatus.NONE);
        } else {
            uiLoader.updateStatus(UILoader.UIStatus.SUCCESS);
        }
        detailListAdapter.setData(track);
    }

    @Override
    public void onAlbumLoaded(Album album) {
        uiLoader.updateStatus(UILoader.UIStatus.LOADING);
        albumId = (int) album.getId();
        albumDetailPresenter.getAlbumDetail(albumId, current_page);
        if (tvAlbumTitle != null) {
            tvAlbumTitle.setText(album.getAlbumTitle());
        }
        if (tvAlbumAuthor != null) {
            tvAlbumAuthor.setText(album.getAnnouncer().getNickname());
        }
        if (coverBg != null && coverBg != null) {
            Picasso.with(this).load(album.getCoverUrlLarge()).into(coverBg, new Callback() {
                @Override
                public void onSuccess() {
                    Drawable drawable = coverBg.getDrawable();
                    if (drawable != null) {
                        L.e(TAG, coverBg.getDrawable() + "++++++++++++++++");
                        //获得图片后，加一个毛玻璃效果
                        ImageBlur.makeBlur(coverBg, DetailActivity.this);
                    }
                }

                @Override
                public void onError() {

                }
            });
        }
        if (smallCover != null) {
            Glide.with(this).load(album.getCoverUrlLarge()).into(smallCover);
        }
    }

    @Override
    public void networkError(int errorCode, String errorMsg) {
        toast(errorMsg);
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
    public void onRetry() {
        albumDetailPresenter.getAlbumDetail(albumId, current_page);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (albumDetailPresenter != null) {
            albumDetailPresenter.unregisterViewCallback(this);
        }
    }

    @Override
    public void onItemClick(List<Track> tracks, int position) {
        //跳转到播放界面
        PlayerPresenter instance = PlayerPresenter.getInstance();
        instance.setPlayList(tracks,position);
        Intent intent = new Intent(DetailActivity.this,PlayerActivity.class);
        startActivity(intent);
    }
}