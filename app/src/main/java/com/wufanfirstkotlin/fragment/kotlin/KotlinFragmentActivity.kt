package com.wufanfirstkotlin.fragment.kotlin

import android.os.Bundle
import com.wufanfirstkotlin.BaseActivity
import com.wufanfirstkotlin.R

class KotlinFragmentActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_fragment)
    }
}