package com.wf.lifecycle.databinding.imageview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.wf.lifecycle.R;
import com.wf.lifecycle.databinding.ActivityImageviewBinding;

/**
 * @author wf
 */
public class ImageviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityImageviewBinding imageviewBinding = DataBindingUtil.setContentView(this, R.layout.activity_imageview);
        //imageviewBinding.setNetworkImage("https://cn.bing.com/th?id=OHR.MackArch_EN-CN7239093764_1920x1080.jpg&rf=LaDigue_1920x1080.jpg");
        imageviewBinding.setLocalImage(R.drawable.star);
    }
}