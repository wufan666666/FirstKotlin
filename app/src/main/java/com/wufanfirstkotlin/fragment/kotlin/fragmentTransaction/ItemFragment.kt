package com.wufanfirstkotlin.fragment.kotlin.fragmentTransaction

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.wufanfirstkotlin.R
import com.wufanfirstkotlin.fragment.kotlin.fragmentviewpager2.FragmentViewPagerActivity
import com.wufanfirstkotlin.himalaya.utils.L

class ItemFragment : Fragment() {

    private var iFragmentCallback: IFragmentCallback? = null


    fun setIFragmentCallback(callback: IFragmentCallback) {
        iFragmentCallback = callback
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_item_list, container, false)
        var button = view.findViewById<Button>(R.id.button)
        button.setOnClickListener {

           /* var receiveMsg: String = iFragmentCallback!!.receiveMsg()
            Toast.makeText(activity?.applicationContext, receiveMsg, Toast.LENGTH_SHORT).show()*/

            iFragmentCallback?.sendMsg("i am from fragment")
            startActivity(Intent(activity?.applicationContext,FragmentViewPagerActivity::class.java))
        }
        return view
    }


}