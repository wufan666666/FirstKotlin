package com.wufanfirstkotlin.Service

import android.app.Service
import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Binder
import android.os.IBinder
import android.util.Log
import com.wufanfirstkotlin.broadcast.MyBroadCast

class VolumeService : Service() {

    var myBroadCast: MyBroadCast = MyBroadCast()
    var conut:Int=0
    private var flag:Boolean =false
    private var myBinder:MyBinder = MyBinder()

    inner class MyBinder:Binder(){
        fun getCount():Int{
            return conut++;
        }
    }


    override fun onBind(intent: Intent): IBinder? {
        Log.e("tag","onBind")
        return myBinder

    }

    override fun onCreate() {
        Log.e("tag","onCreate")
        super.onCreate()
        // 注册广播
        var filter: IntentFilter = IntentFilter()
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION)
        filter.addAction("sendEmptyBroadCast")
        registerReceiver(myBroadCast, filter)

        Thread(){
            kotlin.run {

                while (!flag){
                    try {
                        Thread.sleep(3000)
                    }catch (e:InterruptedException){
                        e.printStackTrace()
                    }
                    conut++;
                }
            }
        }.start()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.e("tag","onStartCommand")
        return super.onStartCommand(intent, flags, startId)

    }

    override fun onRebind(intent: Intent?) {
        Log.e("tag","onRebind")
        super.onRebind(intent)
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.e("tag","onUnbind")
        return true
    }

    override fun onDestroy() {
        Log.e("tag","onDestroy")
        this.flag=true
        super.onDestroy()

        //注销广播
        if (myBroadCast!=null)
        unregisterReceiver(myBroadCast)
    }
}