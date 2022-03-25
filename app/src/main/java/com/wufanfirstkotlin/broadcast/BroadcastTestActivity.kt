package com.wufanfirstkotlin.broadcast

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

import com.wufanfirstkotlin.R

class BroadcastTestActivity : AppCompatActivity() {

    /*1. Standard模式（默认）
    我们平时直接创建的Activity都是这种模式的Activity，这种模式的Activity的特点是：
    只要你创建了Activity实例，一旦激活该Activity，则会向任务栈中加入新创建的实例，退出Activity则会在任务栈中销毁该实例。

    2. SingleTop模式

    这种模式会考虑当前要激活的Activity实例在任务栈中是否正处于栈顶，
    如果处于栈顶则无需重新创建新的实例，会重用已存在的实例，否则会在任务栈中创建新的实例。

    3. SingleTask模式

    如果任务栈中存在该模式的Activity实例，则把栈中该实例以上的Activity实例全部移除，
    调用该实例的newInstance()方法重用该Activity，使该实例处於栈顶位置，否则就重新创建一个新的Activity实例。

    4. SingleInstance模式

    当该模式Activity实例在任务栈中创建后，只要该实例还在任务栈中，即只要激活的是该类型的Activity，
    都会通过调用实例的newInstance()方法重用该Activity，
    此时使用的都是同一个Activity实例，它都会处于任务栈的栈顶。
    此模式一般用于加载较慢的，比较耗性能且不需要每次都重新创建的Activity。
    */
    private lateinit var myBroadcast: MyBroadCast
    private lateinit var getBroadCast: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Log.e("tag","onCreate")
        myBroadcast = MyBroadCast()
        setContentView(R.layout.activity_broadcast_test)
        getBroadCast = findViewById(R.id.getBroadCast)
        //var filter = IntentFilter()
        //filter.addAction("sendEmptyBroadCast")
        //registerReceiver(myBroadcast, filter)
        getBroadCast.setOnClickListener {
            sendBroadcast(Intent("sendEmptyBroadCast"))
            startActivity(Intent(this, BroadCastActivity::class.java))
        }

    }

    /*override fun onStart() {
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

    override fun onResume() {
        super.onResume()
        Log.e("tag","onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.e("tag","onPause")
    }
*/
    override fun onDestroy() {
        super.onDestroy()
        Log.e("tag", "onDestroy+test")
        //unregisterReceiver(myBroadcast)
    }


}
