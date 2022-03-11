package com.wufanfirstkotlin.fragment.kotlin.fragmentwithviewpager2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.wufanfirstkotlin.R

private const val ARG_PARAM1 = "param1"


class BlankFragment : Fragment() {
    private var param1: String? = null
    private var rootview: View? = null
    private var text: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        if (rootview == null) {
            rootview = inflater.inflate(R.layout.fragment_blank, container, false)
        }
        initView(rootview)
        return rootview
    }

    private fun initView(rootview: View?) {
        text = rootview?.findViewById<TextView>(R.id.text)
        text?.text = param1
    }


    companion object {
        @JvmStatic
        fun newInstance(param1: String) =
            BlankFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }
}