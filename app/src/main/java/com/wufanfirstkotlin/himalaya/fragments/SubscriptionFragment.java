package com.wufanfirstkotlin.himalaya.fragments;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wufanfirstkotlin.R;
import com.wufanfirstkotlin.himalaya.base.BaseFragment;

/**
 * @author : wf
 * @date : 2021年10月09日 15:32
 */
public class SubscriptionFragment extends BaseFragment {
    @Override
    protected View onSubViewLoaded(LayoutInflater layoutInflater, ViewGroup container) {
        View inflate = layoutInflater.inflate(R.layout.fragment_subscription, container,false);
        return inflate;
    }
}