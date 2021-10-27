package com.wufanfirstkotlin.himalaya;


import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.wufanfirstkotlin.R;
import com.wufanfirstkotlin.himalaya.base.BaseActivity;
import com.wufanfirstkotlin.himalaya.presenters.PlayerPresenter;

/**
 * @author wf
 */
public class PlayerActivity extends BaseActivity {

    private TextView trackTitle;
    private ViewPager trackViewpager;
    private TextView currentPosition;
    private SeekBar currentSeekbar;
    private TextView totalPosition;
    private ImageView detailTrackIcon;
    private ImageView previousTrackIcon;
    private ImageView playTrackIcon;
    private ImageView nextTrackIcon;
    private ImageView favoriteTrackIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        initView();
        PlayerPresenter playerPresenter = PlayerPresenter.getInstance();
        playerPresenter.play();
    }

    private void initView() {
        trackTitle = findViewById(R.id.track_title);
        trackViewpager = findViewById(R.id.track_viewpager);
        currentPosition = findViewById(R.id.current_position);
        currentSeekbar = findViewById(R.id.current_seekbar);
        totalPosition = findViewById(R.id.total_position);
        detailTrackIcon = findViewById(R.id.detail_track_icon);
        previousTrackIcon = findViewById(R.id.previous_track_icon);
        playTrackIcon = findViewById(R.id.play_track_icon);
        nextTrackIcon = findViewById(R.id.next_track_icon);
        favoriteTrackIcon = findViewById(R.id.favorite_track_icon);

    }
}