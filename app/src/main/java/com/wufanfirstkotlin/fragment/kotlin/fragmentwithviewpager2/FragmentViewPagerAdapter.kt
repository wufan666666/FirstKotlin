package com.wufanfirstkotlin.fragment.kotlin.fragmentwithviewpager2

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class FragmentViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle,val arrayFragment:ArrayList<Fragment>)
    : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return arrayFragment.size
    }

    override fun createFragment(position: Int): Fragment {
        return arrayFragment[position]
    }



}
