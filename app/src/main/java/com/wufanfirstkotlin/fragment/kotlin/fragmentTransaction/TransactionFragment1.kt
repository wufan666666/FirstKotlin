package com.wufanfirstkotlin.fragment.kotlin.fragmentTransaction

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.wufanfirstkotlin.R

class TransactionFragment1 : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var root = inflater.inflate(R.layout.fragment_transaction1, container, false)

        var text_view = root.findViewById<TextView>(R.id.text_view)

        var arguments = this.arguments
        var value: String? = arguments?.getString("key")
        text_view.text = value
        return root
    }

}