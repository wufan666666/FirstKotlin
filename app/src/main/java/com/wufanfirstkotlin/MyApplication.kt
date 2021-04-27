package com.wufanfirstkotlin

import android.app.Application
import android.content.Intent
import com.wufanfirstkotlin.Service.VolumeService
import com.wufanfirstkotlin.sqlite.MyDBOpenHelper

class MyApplication:Application() {
    lateinit var  myDBHelper:MyDBOpenHelper;
    override fun onCreate() {
        super.onCreate()
        //Classifier 'MyDBOpenHelper' does not have a companion object, and thus must be initialized here
        //startService(Intent(this,VolumeService::class.java))
       // myDBHelper = MyDBOpenHelper(applicationContext,"my.db",null,1);
       // myDBHelper.writableDatabase
    }
}