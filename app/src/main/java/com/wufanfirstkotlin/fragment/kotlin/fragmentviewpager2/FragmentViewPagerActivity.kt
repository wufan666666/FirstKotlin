package com.wufanfirstkotlin.fragment.kotlin.fragmentviewpager2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.wufanfirstkotlin.R

/**
 * kotlin配合viewpager2的使用
 */
class FragmentViewPagerActivity : AppCompatActivity() {
    var viewpager: ViewPager2? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_view_pager)
        viewpager = findViewById(R.id.viewpager)

        var arrayList = ArrayList<String>()
        arrayList.add("i")
        arrayList.add("L")
        arrayList.add("o")
        arrayList.add("v")
        arrayList.add("e")
        arrayList.add("y")
        arrayList.add("o")
        arrayList.add("u")
        viewpager?.adapter = ViewPagerAdapter(this,arrayList)
    }
}