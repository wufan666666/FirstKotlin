package com.wufanfirstkotlin.sqlite.sharedpreference;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.wufanfirstkotlin.BaseActivity;
import com.wufanfirstkotlin.R;

public class SharedpreferenceActivity extends BaseActivity {

    private SharedPreferences mSharedpreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharedpreference);
        mSharedpreference = getSharedPreferences("my_Sharedpreference", MODE_PRIVATE);
    }

    public void saveSharedPreference(View view) {
        SharedPreferences.Editor edit = mSharedpreference.edit();
        edit.putString("string",getPackageName());
        edit.putBoolean("boolean",true);
        edit.putInt("int",Integer.MAX_VALUE);
        edit.putFloat("float",Float.MAX_VALUE);
        edit.putLong("long",Long.MAX_VALUE);
        edit.apply();
        toast("数据已经被存入/data/data/app package name/shared_prefs 目录下");
    }

    public void getSharedPreference(View view) {
        String s1 = mSharedpreference.getString("string", "暂无数据");
        Boolean s2 = mSharedpreference.getBoolean("boolean", false);
        int s3 = mSharedpreference.getInt("int", Integer.MIN_VALUE);
        float s4 = mSharedpreference.getFloat("float", Float.MIN_VALUE);
        long s5= mSharedpreference.getLong("long", Long.MIN_VALUE);
        toast(s1+"\n"+s2+"\n"+s3+"\n"+s4+"\n"+s5);
    }
}