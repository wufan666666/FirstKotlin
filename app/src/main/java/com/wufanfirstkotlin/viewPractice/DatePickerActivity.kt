package com.wufanfirstkotlin.viewPractice

import android.app.Activity
import android.os.Bundle
import android.widget.DatePicker
import android.widget.Toast
import com.wufanfirstkotlin.R

class DatePickerActivity : Activity() {

    var year: Int = 0
    var month: Int = 0
    var day: Int = 0
    lateinit var datepicker: DatePicker
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_date_picker)
        datepicker = findViewById(R.id.date_picker)
        year = datepicker.year
        month = datepicker.month
        day = datepicker.dayOfMonth
        datepicker.init(year, month, day,
            DatePicker.OnDateChangedListener { view, year, monthOfYear, dayOfMonth ->
                this.year = year
                this.month = month
                this.day = dayOfMonth
                Toast.makeText(
                    applicationContext,
                    "" + this.year + "年" + (this.month + 1) + "月" + this.day + "日",
                    Toast.LENGTH_SHORT
                ).show()
            })
    }
}