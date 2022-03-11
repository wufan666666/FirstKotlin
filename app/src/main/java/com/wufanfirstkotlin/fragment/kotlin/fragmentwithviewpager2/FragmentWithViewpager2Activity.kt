package com.wufanfirstkotlin.fragment.kotlin.fragmentwithviewpager2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.wufanfirstkotlin.R

class FragmentWithViewpager2Activity : AppCompatActivity(), View.OnClickListener {

    var ll_weixin: LinearLayout? = null
    var ll_mail: LinearLayout? = null
    var ll_find: LinearLayout? = null
    var ll_my: LinearLayout? = null

    var iv_weixin: ImageView? = null
    var iv_mail: ImageView? = null
    var iv_find: ImageView? = null
    var iv_my: ImageView? = null
    var iv_current: ImageView? = null
    var viewpager:ViewPager2? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_with_viewpager2)

        viewpager = findViewById(R.id.viewpager)
        var fragmentList = ArrayList<Fragment>()
        fragmentList.add(BlankFragment.newInstance("聊天"))
        fragmentList.add(BlankFragment.newInstance("通讯录"))
        fragmentList.add(BlankFragment.newInstance("朋友圈"))
        fragmentList.add(BlankFragment.newInstance("我的"))
        viewpager?.adapter =
            FragmentViewPagerAdapter(supportFragmentManager, lifecycle, fragmentList)
        initBottom()
        iv_current = iv_weixin
        iv_weixin?.isSelected = true
        ll_weixin?.setOnClickListener(this)
        ll_mail?.setOnClickListener(this)
        ll_find?.setOnClickListener(this)
        ll_my?.setOnClickListener(this)
        viewpager?.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageSelected(position: Int) {
                changeViewpager(position)
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
            }
        })
    }

    private fun changeViewpager(position: Int) {
        iv_current?.isSelected = false
        when (position) {
            0, R.id.ll_weixin -> {
                iv_weixin?.isSelected = true
                viewpager?.currentItem = 0
                iv_current = iv_weixin
            }
            1, R.id.ll_mail -> {
                iv_mail?.isSelected = true
                viewpager?.currentItem = 1
                iv_current = iv_mail
            }
            2, R.id.ll_find -> {
                iv_find?.isSelected = true
                viewpager?.currentItem = 2
                iv_current = iv_find
            }
            3, R.id.ll_my -> {
                iv_my?.isSelected = true
                viewpager?.currentItem = 3
                iv_current = iv_my
            }
        }
    }

    private fun initBottom() {
        ll_weixin = findViewById(R.id.ll_weixin)
        ll_mail = findViewById(R.id.ll_mail)
        ll_find = findViewById(R.id.ll_find)
        ll_my = findViewById(R.id.ll_my)

        iv_weixin = findViewById(R.id.img_weixin)
        iv_mail = findViewById(R.id.img_mail)
        iv_find = findViewById(R.id.img_find)
        iv_my = findViewById(R.id.img_my)
    }

    override fun onClick(view: View?) {
        changeViewpager(view!!.id)
    }
}