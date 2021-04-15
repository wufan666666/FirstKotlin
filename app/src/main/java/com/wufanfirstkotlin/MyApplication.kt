package com.wufanfirstkotlin

import android.app.Application
import android.content.Intent
import com.wufanfirstkotlin.Service.VolumeService

class MyApplication:Application() {

    override fun onCreate() {
        super.onCreate()
        //startService(Intent(this,VolumeService::class.java))
    }
}