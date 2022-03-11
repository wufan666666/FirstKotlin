package com.wufanfirstkotlin.fragment.kotlin.fragmentwithviewpager2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.wufanfirstkotlin.R

class FragmentWithViewpager2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_with_viewpager2)

        var viewpager = findViewById<ViewPager2>(R.id.viewpager)
        var fragmentList = ArrayList<Fragment>()
        fragmentList.add(BlankFragment.newInstance("聊天"))
        fragmentList.add(BlankFragment.newInstance("通讯录"))
        fragmentList.add(BlankFragment.newInstance("朋友圈"))
        fragmentList.add(BlankFragment.newInstance("我的"))
        viewpager.adapter = FragmentViewPagerAdapter(supportFragmentManager,lifecycle,fragmentList)
    }
}