package com.wf.lifecycle.lifeCycle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.wf.lifecycle.R;

/**
 * @author wf
 */
public class LifecycleServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle_service);
    }

    public void startGps(View view) {
        startService(new Intent(this,MyLocationService.class));

    }

    public void stopGps(View view) {
        stopService(new Intent(this,MyLocationService.class));
    }
}