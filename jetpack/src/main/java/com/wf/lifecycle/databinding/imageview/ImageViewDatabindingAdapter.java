package com.wf.lifecycle.databinding.imageview;

import android.graphics.Color;
import android.text.TextUtils;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import com.wf.lifecycle.R;

/**
 * @author : wf
 * @date : 2021年11月08日 15:19
 */
public class ImageViewDatabindingAdapter {

   /* @BindingAdapter("image")
    public static void setImageView(ImageView imageView ,String url){
        if (!TextUtils.isEmpty(url)) {
            Picasso.get().load(url)
                    .placeholder(R.drawable.cat)
                    .into(imageView);
        }else {
            imageView.setBackgroundColor(Color.GRAY);
        }
    }*/

    @BindingAdapter(value = {"image","localImageView"},requireAll = false)
    public static void setImageView(ImageView imageView, String url,int resId) {
        if (!TextUtils.isEmpty(url)) {
            Picasso.get().load(url)
                    .placeholder(R.drawable.cat)
                    .into(imageView);
        } else {
            imageView.setImageResource(resId);
        }
    }

}