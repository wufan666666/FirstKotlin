package com.wufanfirstkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.wufanfirstkotlin.Service.ServiceIntentActivity
import com.wufanfirstkotlin.Service.ServiceJavaActivity
import com.wufanfirstkotlin.broadcast.BroadCastActivity
import com.wufanfirstkotlin.fragment.FragmentActivity
import com.wufanfirstkotlin.sqlite.SqliteActivity
import com.wufanfirstkotlin.viewPractice.CheckBoxActivity
import com.wufanfirstkotlin.viewPractice.DatePickerActivity
import com.wufanfirstkotlin.viewPractice.DialogActivity
import com.wufanfirstkotlin.viewPractice.WebViewActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var webviewbt: Button
    private lateinit var dialog: Button
    private lateinit var checkbox: Button
    private lateinit var datePicker: Button
    private lateinit var broadcast: Button
    private lateinit var service: Button
    private lateinit var intentservice: Button
    private lateinit var fragment: Button
    private lateinit var sqLite: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        webviewbt = findViewById(R.id.web_view_bt)
        dialog = findViewById(R.id.dialog)
        checkbox = findViewById(R.id.checkbox)
        datePicker = findViewById(R.id.date_picker)
        broadcast = findViewById(R.id.broadcast)
        service = findViewById(R.id.service)
        intentservice = findViewById(R.id.intent_service)
        fragment = findViewById(R.id.fragment)
        sqLite = findViewById(R.id.sqLite)
        webviewbt.setOnClickListener(this)
        dialog.setOnClickListener(this)
        checkbox.setOnClickListener(this)
        datePicker.setOnClickListener(this)
        broadcast.setOnClickListener(this)
        service.setOnClickListener(this)
        intentservice.setOnClickListener(this)
        fragment.setOnClickListener(this)
        sqLite.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.checkbox -> startActivity(Intent(this, CheckBoxActivity::class.java))

            R.id.web_view_bt -> startActivity(Intent(this, WebViewActivity::class.java))

            R.id.dialog -> startActivity(Intent(this, DialogActivity::class.java))

            R.id.date_picker -> startActivity(Intent(this, DatePickerActivity::class.java))

            R.id.broadcast -> startActivity(Intent(this, BroadCastActivity::class.java))

            R.id.service -> startActivity(Intent(this, ServiceJavaActivity::class.java))

            R.id.intent_service -> startActivity(Intent(this, ServiceIntentActivity::class.java))

            R.id.fragment -> startActivity(Intent(this, FragmentActivity::class.java))

            R.id.sqLite -> startActivity(Intent(this, SqliteActivity::class.java))


        }
    }

}


