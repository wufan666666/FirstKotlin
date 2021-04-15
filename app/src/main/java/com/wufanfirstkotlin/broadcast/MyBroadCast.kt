package com.wufanfirstkotlin.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.util.Log
import android.widget.Toast

class MyBroadCast : BroadcastReceiver() {

    private var connectActivity: String = ConnectivityManager.CONNECTIVITY_ACTION
    private var sendEmptyBroadCast: String = "sendEmptyBroadCast"
    override fun onReceive(context: Context?, intent: Intent?) {
        var action = intent?.action
        when (action) {
            connectActivity -> {
                Toast.makeText(context, "connectActivity", Toast.LENGTH_SHORT).show()
            }
            sendEmptyBroadCast ->{
                Toast.makeText(context,"接收到广播了",Toast.LENGTH_SHORT).show()
            }

        }
    }

}