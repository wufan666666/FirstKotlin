package com.wufanfirstkotlin.himalaya.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.wufanfirstkotlin.himalaya.utils.FragmentCreator;

import org.jetbrains.annotations.NotNull;

/**
 * @author : wf
 * @date : 2021年10月09日 15:22
 */
public class MainContentAdapter extends FragmentPagerAdapter {


    public MainContentAdapter(@NonNull @NotNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @NotNull
    @Override
    public Fragment getItem(int position) {
        return FragmentCreator.getFragment(position);
    }

    @Override
    public int getCount() {
        return FragmentCreator.PAGE_COUNT;
    }
}