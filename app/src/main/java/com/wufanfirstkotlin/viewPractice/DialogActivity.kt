package com.wufanfirstkotlin.viewPractice

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.wufanfirstkotlin.R

class DialogActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var btui: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog)
        btui = findViewById(R.id.button)
        btui.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.button -> {
                AlertDialog.Builder(this).apply {
                    setIcon(R.mipmap.blue_up)
                        .setTitle("这是一个dialog")
                        .setMessage("a /n b /n")
                        .setNegativeButton("取消") { _, _ ->
                        }
                        .setPositiveButton("确定") { _, _ ->
                        }
                        .show()
                }

            }
        }
    }
}