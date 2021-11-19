package com.wf.lifecycle.databinding.imageview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;

import com.wf.lifecycle.R;
import com.wf.lifecycle.databinding.ActivityImageviewBinding;
import com.wf.lifecycle.databinding.editView.User;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author wf
 */
public class ImageviewActivity extends AppCompatActivity {
    int i =0;
    List<User> list= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        while (true){
            User user = new User("111");
            list.add(user);
            Log.e("ddd",list.get(i++).userName);
        }
        //ActivityImageviewBinding imageviewBinding = DataBindingUtil.setContentView(this, R.layout.activity_imageview);
        //imageviewBinding.setNetworkImage("https://cn.bing.com/th?id=OHR.MackArch_EN-CN7239093764_1920x1080.jpg&rf=LaDigue_1920x1080.jpg");
        //imageviewBinding.setLocalImage(R.drawable.star);
    }
}