package com.wufanfirstkotlin.himalaya;


import android.os.Bundle;
import android.os.Trace;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.wufanfirstkotlin.R;
import com.wufanfirstkotlin.himalaya.adapters.PlayTrackAdapter;
import com.wufanfirstkotlin.himalaya.base.BaseActivity;
import com.wufanfirstkotlin.himalaya.interfaces.IPlayerViewCallback;
import com.wufanfirstkotlin.himalaya.presenters.PlayerPresenter;
import com.wufanfirstkotlin.himalaya.utils.L;
import com.ximalaya.ting.android.opensdk.model.track.Track;
import com.ximalaya.ting.android.opensdk.player.service.XmPlayListControl;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author wf
 */
public class PlayerActivity extends BaseActivity implements IPlayerViewCallback, ViewPager.OnPageChangeListener {

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
    private PlayerPresenter mPlayerPresenter;
    private SimpleDateFormat minFormat = new SimpleDateFormat("mm:ss");
    private SimpleDateFormat hourFormat = new SimpleDateFormat("hh:mm:ss");
    private String mTotalTime;
    private String TAG = "PlayerActivity";
    private int mCurrentProgress;
    private boolean isUserUpdateProgress = false;
    private PlayTrackAdapter mPlayTrackAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        getWindow().getDecorView().setSystemUiVisibility(View.INVISIBLE);
        initView();
        mPlayerPresenter = PlayerPresenter.getInstance();
        mPlayerPresenter.registerViewCallback(this);
        initEvent();
        playVoice();
        mPlayerPresenter.getPlayList();

    }

    private void playVoice() {
        mPlayerPresenter.play();
        playTrackIcon.setImageResource(R.mipmap.pause);
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
        mPlayTrackAdapter = new PlayTrackAdapter();
        trackViewpager.setAdapter(mPlayTrackAdapter);
    }

    private void initEvent() {
        playTrackIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPlayerPresenter.isPlay()) {
                    mPlayerPresenter.pause();
                } else {
                    mPlayerPresenter.play();
                }
            }
        });
        currentSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser){
                    mCurrentProgress = progress;
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                isUserUpdateProgress = true;
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                isUserUpdateProgress = false;
                mPlayerPresenter.seekTo(mCurrentProgress);
            }
        });

        previousTrackIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPlayerPresenter != null) {
                    mPlayerPresenter.playPre();
                }
            }
        });
        nextTrackIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPlayerPresenter != null) {
                    mPlayerPresenter.playNext();
                }
            }
        });

        trackViewpager.addOnPageChangeListener(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPlayerPresenter != null) {
            mPlayerPresenter.unregisterViewCallback(this);
            mPlayerPresenter = null;
        }
    }

    @Override
    public void onPlayStart() {
        if (mPlayerPresenter != null) {
            playTrackIcon.setImageResource(R.mipmap.pause);
        }
    }

    @Override
    public void onPlayStop() {
        if (mPlayerPresenter != null) {
            playTrackIcon.setImageResource(R.mipmap.bottom_play_3);
        }
    }

    @Override
    public void onPlayPause() {
        if (mPlayerPresenter != null) {
            playTrackIcon.setImageResource(R.mipmap.bottom_play_3);
        }
    }

    @Override
    public void onPlayError() {

    }

    @Override
    public void nextPlay(Track track) {

    }

    @Override
    public void prePlay(Track track) {

    }

    @Override
    public void onListLoaded(List<Track> list) {
        if (mPlayTrackAdapter != null) {
            mPlayTrackAdapter.setData(list);
        }
    }

    @Override
    public void onPlayModeChange(XmPlayListControl.PlayMode playMode) {

    }

    @Override
    public void onProgressChange(int currentProgress, int total) {
        currentSeekbar.setMax(total);
        //设置播放时间
        int oneHour = 1000 * 60 * 60;
        String mTotalTime;
        String currentPositionTime;
        if (total > oneHour) {
            mTotalTime = hourFormat.format(total);
            currentPositionTime = hourFormat.format(currentProgress);
        }else {
            mTotalTime = minFormat.format(total);
            currentPositionTime = minFormat.format(currentProgress);
        }

        if (currentPosition!=null){
            currentPosition.setText(currentPositionTime);
        }
        if (totalPosition!=null){
            totalPosition.setText(mTotalTime);
        }
        //更新进度条
        if (!isUserUpdateProgress) {
            currentSeekbar.setProgress(currentProgress);
        }

    }

    @Override
    public void onAdLoading() {

    }

    @Override
    public void onAdLoaded() {

    }

    @Override
    public void onUpdateTrack(Track track) {
        if (trackTitle != null) {
            trackTitle.setText(track.getTrackTitle());
        }
        if (trackViewpager!=null){

        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (mPlayerPresenter != null) {
            mPlayerPresenter.playByIndex(position);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}