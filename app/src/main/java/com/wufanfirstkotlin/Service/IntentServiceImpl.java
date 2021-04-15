package com.wufanfirstkotlin.Service;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

/**
 * @author : wf
 * @date : 2021年04月14日 17:07
 */
public class IntentServiceImpl extends IntentService {
    String TAG = "tag";

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public IntentServiceImpl() {
        super("IntentServiceImpl");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String action = intent.getExtras().getString("param");
        if (action.equals("s1")) Log.i(TAG, "启动service1");
        else if (action.equals("s2")) Log.i(TAG, "启动service2");
        else if (action.equals("s3")) Log.i(TAG, "启动service3");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //重写其他方法,用于查看方法的调用顺序
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind");
        return super.onBind(intent);
    }

    @Override
    public void onCreate() {
        Log.i(TAG, "onCreate");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void setIntentRedelivery(boolean enabled) {
        super.setIntentRedelivery(enabled);
        Log.i(TAG, "setIntentRedelivery");
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy");
        super.onDestroy();
    }
}