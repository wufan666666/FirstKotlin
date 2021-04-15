package com.wufanfirstkotlin.fragment.pagerViewFragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * @author : wf
 * @date : 2021年04月15日 16:18
 */
public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    private final int PAGER_COUNT = 4;
    private MyFragmentPager1 myFragment1 = null;
    private MyFragmentPager2 myFragment2 = null;
    private MyFragmentPager3 myFragment3 = null;
    private MyFragmentPager4 myFragment4 = null;

    public MyFragmentPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
        myFragment1 = new MyFragmentPager1("第一个Fragment");
        myFragment2 = new MyFragmentPager2("第贰个Fragment");
        myFragment3 = new MyFragmentPager3("第仨个Fragment");
        myFragment4 = new MyFragmentPager4("第肆个Fragment");
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = myFragment1;
                break;
            case 1:
                fragment = myFragment2;
                break;
            case 2:
                fragment = myFragment3;
                break;
            case 3:
                fragment = myFragment4;
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return PAGER_COUNT;
    }
}