package com.wufanfirstkotlin.fragment.kotlin.fragmentviewpager2

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wufanfirstkotlin.R
import com.wufanfirstkotlin.fragment.kotlin.fragmentwithviewpager2.FragmentWithViewpager2Activity

class ViewPagerAdapter(val context: Context, arrayList: ArrayList<String>) : RecyclerView.Adapter<ViewPagerAdapter.InnerViewHolder>() {

    private var arrayList = arrayList

    override fun getItemCount(): Int {
        return this.arrayList.size
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerViewHolder {
        return InnerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_viewpager2, parent, false))
    }

    override fun onBindViewHolder(holder: InnerViewHolder, position: Int) {
        holder.textview.text = arrayList.get(position)
        holder.textview.setOnClickListener {
            context.startActivity(Intent(context,FragmentWithViewpager2Activity::class.java))
        }
    }

    class InnerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var textview = itemView.findViewById<TextView>(R.id.textview)
    }

}
