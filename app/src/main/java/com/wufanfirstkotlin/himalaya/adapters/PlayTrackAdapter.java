package com.wufanfirstkotlin.himalaya.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.wufanfirstkotlin.R;
import com.wufanfirstkotlin.himalaya.utils.L;
import com.ximalaya.ting.android.opensdk.model.track.Track;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : wf
 * @date : 2021年10月28日 16:21
 */
public class PlayTrackAdapter extends PagerAdapter {
    private List<Track> mData = new ArrayList<>();
    private ImageView mImageViewPager;
    private String TAG = "PlayTrackAdapter";

    @NonNull
    @NotNull
    @Override
    public Object instantiateItem(@NonNull @NotNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_track_pager, container,false);
        container.addView(view);
        mImageViewPager = view.findViewById(R.id.image_view_pager);
        Track track = mData.get(position);
        String coverUrlLarge = track.getCoverUrlLarge();
        Glide.with(container.getContext()).load(coverUrlLarge).into(mImageViewPager);
        L.e(TAG,coverUrlLarge);
        return view;
    }

    @Override
    public void destroyItem(@NonNull @NotNull ViewGroup container, int position, @NonNull @NotNull Object object) {
       container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull @NotNull View view, @NonNull @NotNull Object object) {
        return view == object;
    }

    public void setData(List<Track> list) {
        mData.clear();
        this.mData = list;
        notifyDataSetChanged();
    }

}