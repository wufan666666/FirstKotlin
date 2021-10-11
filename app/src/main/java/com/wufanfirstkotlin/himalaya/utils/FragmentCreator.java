package com.wufanfirstkotlin.himalaya.utils;

import com.wufanfirstkotlin.himalaya.base.BaseFragment;
import com.wufanfirstkotlin.himalaya.fragments.HistoryFragment;
import com.wufanfirstkotlin.himalaya.fragments.ReCommonFragment;
import com.wufanfirstkotlin.himalaya.fragments.SubscriptionFragment;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : wf
 * @date : 2021年10月09日 15:43
 */
public class FragmentCreator {
    public final static int INDEX_RECOMMEND = 0;
    public final static int INDEX_SUBSCRIPTION = 1;
    public final static int INDEX_HISTORY = 2;
    public final static int PAGE_COUNT = 3;


    private static Map<Integer, BaseFragment> fragmentCache = new HashMap<>();


    public static BaseFragment getFragment(int index) {
        BaseFragment baseFragment = fragmentCache.get(index);
        if (baseFragment == null) {
            switch (index) {
                case INDEX_RECOMMEND:
                    baseFragment = new ReCommonFragment();
                    break;
                case INDEX_SUBSCRIPTION:
                    baseFragment = new SubscriptionFragment();
                    break;
                case INDEX_HISTORY:
                    baseFragment = new HistoryFragment();
                    break;
            }
            fragmentCache.put(index, baseFragment);
        }
        return baseFragment;
    }
}