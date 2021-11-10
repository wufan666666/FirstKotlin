package com.wf.lifecycle.lifeCycle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.wf.lifecycle.R;

/**
 * @author wf
 */
public class MainActivity extends AppCompatActivity {

    private MyChronometer mChronometer;
    private long mElapsedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mChronometer = findViewById(R.id.my_chronometer);
        getLifecycle().addObserver(mChronometer);
    }

}