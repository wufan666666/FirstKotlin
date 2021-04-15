package com.wufanfirstkotlin.Service

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import com.wufanfirstkotlin.R

class ServiceIntentActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent_service)

        var intent1 = Intent("com.wufanfirstkotlin.Service.ServiceIntentActivity")
        var bundle1 = Bundle()
        intent1.setPackage(packageName)
        bundle1.putString("param","s1")
        intent1.putExtras(bundle1)


        var intent2 = Intent("com.wufanfirstkotlin.Service.ServiceIntentActivity")
        var bundle2 = Bundle()
        intent2.setPackage(packageName)
        bundle2.putString("param","s2")
        intent2.putExtras(bundle2)


        var intent3 = Intent("com.wufanfirstkotlin.Service.ServiceIntentActivity")
        var bundle3= Bundle()
        intent3.setPackage(packageName)
        bundle3.putString("param","s3")
        intent3.putExtras(bundle3)


        startService(intent1)
        startService(intent2)
        startService(intent3)


    }

}