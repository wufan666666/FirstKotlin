package com.wufanfirstkotlin.broadcast

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.wufanfirstkotlin.R

class BroadCastActivity : AppCompatActivity() {

    lateinit var broadcastbtn: Button
    lateinit var broadcastbtntest: Button
    lateinit var broadcastbtnowner: Button
    private lateinit var myBroadcast:MyBroadCast
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("tag","onCreate")
        setContentView(R.layout.activity_broad_cast)
        broadcastbtn = findViewById(R.id.broadcast_btn)
        broadcastbtntest = findViewById(R.id.intent)
        broadcastbtnowner = findViewById(R.id.intent_owner)
        myBroadcast= MyBroadCast()
       var filter= IntentFilter()
       filter.addAction("sendEmptyBroadCast")
       registerReceiver(myBroadcast,filter)
        broadcastbtn.setOnClickListener {
            sendBroadcast(Intent("sendEmptyBroadCast"))
            Toast.makeText(applicationContext,"发送了广播",Toast.LENGTH_SHORT).show()
        }
        broadcastbtnowner.setOnClickListener {
            startActivity(Intent(this,BroadCastActivity::class.java))
        }


        broadcastbtntest.setOnClickListener {
            startActivity(Intent(this,BroadcastTestActivity::class.java))
        }
    }

    override fun onStart() {
        super.onStart()
        Log.e("tag","onStart")
    }

    override fun onStop() {
        super.onStop()
        Log.e("tag","onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.e("tag","onRestart")
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.e("tag","onNewIntent")
    }
    override fun onResume() {
        super.onResume()
        Log.e("tag","onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.e("tag","onPause")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("tag","onDestroy")
        unregisterReceiver(myBroadcast)
    }

}
