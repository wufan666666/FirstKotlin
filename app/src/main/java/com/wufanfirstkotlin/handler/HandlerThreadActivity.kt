package com.wufanfirstkotlin.handler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.os.Message
import android.widget.Button
import com.wufanfirstkotlin.R
import com.wufanfirstkotlin.himalaya.utils.L

class HandlerThreadActivity : AppCompatActivity() {

    private var handlerThread: HandlerThread? = null
    private var mHandler: Handler? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_handler_thread)
        handlerThread = HandlerThread("myHandlerThread")

        handlerThread!!.start()

        mHandler = Handler(handlerThread!!.looper, Handler.Callback {
            when (it.what) {
                0x1 -> {
                    for (i in 1..5) {
                        Thread.sleep(3000)
                        L.e("测试", "第${i}次：执行了三秒的睡眠操作")
                    }
                    false
                }
                else -> {
                    false
                }
            }

        })

        mHandler!!.sendEmptyMessage(0x1)

        findViewById<Button>(R.id.quit)
            .setOnClickListener {
                //handlerThread!!.quit()
                mHandler!!.sendEmptyMessage(0x1)
            }
    }
}