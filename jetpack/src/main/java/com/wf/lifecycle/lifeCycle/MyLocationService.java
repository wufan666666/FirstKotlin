package com.wf.lifecycle.lifeCycle;


import android.util.Log;

import androidx.lifecycle.LifecycleService;

/**
 * @author : wf
 * @date : 2021年11月02日 15:31
 */
public class MyLocationService extends LifecycleService {
    public MyLocationService() {
        Log.d("wf","MyLocationService");
        MyLocationObserver locationObserver = new MyLocationObserver(this);
        getLifecycle().addObserver(locationObserver);
    }

}