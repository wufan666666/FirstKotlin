package com.wufanfirstkotlin

import android.app.Application
import android.content.Intent
import com.wufanfirstkotlin.Service.VolumeService
import com.wufanfirstkotlin.sqlite.MyDBOpenHelper

class MyApplication:Application() {
    lateinit var  myDBHelper:MyDBOpenHelper
}