package com.wufanfirstkotlin.fragment.kotlin

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.wufanfirstkotlin.R
import com.wufanfirstkotlin.fragment.kotlin.fragmentTransaction.FragmentTransactionActivity

class MyFragment1 : Fragment() {

    private var imageview: ImageView? = null
    private var textview: TextView? = null
    private var inflate: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        inflate = inflater.inflate(R.layout.item_fragment, container, false)
        textview = inflate?.findViewById<TextView>(R.id.num)
        imageview = inflate?.findViewById<ImageView>(R.id.image)

        textview?.text="i am fragment1"

        textview?.setOnClickListener{
            startActivity(Intent(activity,FragmentTransactionActivity::class.java))
        }
        return inflate
    }
}