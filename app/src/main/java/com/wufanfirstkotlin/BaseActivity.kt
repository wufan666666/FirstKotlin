package com.wufanfirstkotlin

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import javax.security.auth.callback.Callback

open class BaseActivity : AppCompatActivity(),Callback {


    fun toast(msg:String){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
    }
}