package com.wf.lifecycle.liveData.fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.wf.lifecycle.R;

public class FragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
    }
}