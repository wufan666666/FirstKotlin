package com.wufanfirstkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import com.wufanfirstkotlin.yufalianxi.CheckBoxActivity
import com.wufanfirstkotlin.yufalianxi.DatePickerActivity
import com.wufanfirstkotlin.yufalianxi.DialogActivity
import com.wufanfirstkotlin.yufalianxi.WebViewActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var webviewbt: Button
    private lateinit var dialog: Button
    private lateinit var checkbox: Button
    private lateinit var datePicker: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        webviewbt = findViewById(R.id.web_view_bt)
        dialog = findViewById(R.id.dialog)
        checkbox = findViewById(R.id.checkbox)
        datePicker = findViewById(R.id.date_picker)
        webviewbt.setOnClickListener(this)
        dialog.setOnClickListener(this)
        checkbox.setOnClickListener(this)
        datePicker.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.checkbox -> startActivity(Intent(this, CheckBoxActivity::class.java))

            R.id.web_view_bt -> startActivity(Intent(this, WebViewActivity::class.java))

            R.id.dialog -> startActivity(Intent(this, DialogActivity::class.java))

            R.id.date_picker -> startActivity(Intent(this, DatePickerActivity::class.java))


        }
    }

}


