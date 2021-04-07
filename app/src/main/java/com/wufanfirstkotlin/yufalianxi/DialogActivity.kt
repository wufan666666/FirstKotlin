package com.wufanfirstkotlin.yufalianxi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.wufanfirstkotlin.R

class DialogActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var btui:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog)
        btui = findViewById(R.id.button)
        btui.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.button -> {
                
            }
        }
    }
}