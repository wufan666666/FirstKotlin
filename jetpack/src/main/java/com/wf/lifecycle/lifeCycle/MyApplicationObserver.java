package com.wf.lifecycle.lifeCycle;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * @author : wf
 * @date : 2021年11月03日 9:35
 */
public class MyApplicationObserver implements LifecycleObserver {
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private void onCreate() {
        Log.d("wf", "ON_CREATE");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    private void onPause() {
        Log.d("wf", "ON_PAUSE");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private void onResume() {
        Log.d("wf", "ON_RESUME");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private void onStart() {
        Log.d("wf", "ON_START");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    private void onStop() {
        Log.d("wf", "ON_STOP");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private void onDestroy() {
        Log.d("wf", "ON_DESTROY");
    }
}