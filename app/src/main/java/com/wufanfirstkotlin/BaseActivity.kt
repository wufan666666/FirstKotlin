package com.wufanfirstkotlin

import android.content.Context
import android.os.Handler
import android.os.Message
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.lang.ref.WeakReference
import javax.security.auth.callback.Callback

open class BaseActivity : AppCompatActivity(),Callback {


    fun toast(msg:String){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
    }

    companion object{
        class InnerHandler : Handler {
            private var reference :WeakReference<Context>? = null

            constructor(context : Context){
                reference = WeakReference<Context>(context)
            }

            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
            }
        }
    }
}