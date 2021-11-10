package com.wf.lifecycle.lifeCycle;

import android.content.Context;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.widget.Chronometer;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * @author : wf
 * @date : 2021年11月01日 16:00
 *
 */
public class MyChronometer extends Chronometer implements LifecycleObserver {
    private long mElapsedTime;

    public MyChronometer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private void startCH(){
        setBase(SystemClock.elapsedRealtime() - mElapsedTime);
        start();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    private void endCH() {
        mElapsedTime = SystemClock.elapsedRealtime() - getBase();
        stop();
    }
}