package com.wf.lifecycle.lifeCycle;

import android.app.Application;

import androidx.lifecycle.ProcessLifecycleOwner;

/**
 * @author : wf
 * @date : 2021年11月03日 9:35
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ProcessLifecycleOwner.get().getLifecycle()
                .addObserver(new MyApplicationObserver());
    }
}