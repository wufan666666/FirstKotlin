package com.wufanfirstkotlin.himalaya;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.wufanfirstkotlin.BaseActivity;
import com.wufanfirstkotlin.R;
import com.wufanfirstkotlin.himalaya.interfaces.IAlbumDetailViewCallback;
import com.wufanfirstkotlin.himalaya.presenters.AlbumDetailPresenter;
import com.wufanfirstkotlin.himalaya.utils.ImageBlur;
import com.wufanfirstkotlin.himalaya.utils.L;
import com.wufanfirstkotlin.himalaya.views.CustomRoundAngleImageView;
import com.ximalaya.ting.android.opensdk.model.album.Album;
import com.ximalaya.ting.android.opensdk.model.track.Track;

import java.util.List;

/**
 * @author wf
 */
public class DetailActivity extends BaseActivity implements IAlbumDetailViewCallback {

    private ImageView coverBg;
    private CustomRoundAngleImageView smallCover;
    private TextView tvAlbumTitle;
    private TextView tvAlbumAuthor;
    private String TAG = "DetailActivity";
    private AlbumDetailPresenter albumDetailPresenter;
    private int current_page =1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        initView();
        albumDetailPresenter = AlbumDetailPresenter.getInstance();
        albumDetailPresenter.registerViewCallback(this);
    }

    private void initView() {
        coverBg = findViewById(R.id.cover_bg);
        smallCover = findViewById(R.id.small_cover);
        tvAlbumTitle = findViewById(R.id.tv_album_title);
        tvAlbumAuthor = findViewById(R.id.tv_album_author);
    }

    @Override
    public void onDetailListLoad(List<Track> track) {

    }

    @Override
    public void onAlbumLoaded(Album album) {
        albumDetailPresenter.getAlbumDetail((int) album.getId(), current_page);
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
}