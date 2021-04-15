package com.wufanfirstkotlin.Service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.wufanfirstkotlin.R;

/**
 * @author : wf
 * @date : 2021年04月14日 16:43
 */
public class ServiceJavaActivity extends AppCompatActivity {

    private VolumeService.MyBinder binder;
    private Button bindService;
    private Button unbindService;
    private Button serviceStatus;
    private Button serviceStart;
    private Button serviceClose;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        bindService = (Button) findViewById(R.id.bind_service);
        unbindService = (Button) findViewById(R.id.unbind_service);
        serviceStatus = (Button) findViewById(R.id.service_status);
        serviceStart = (Button) findViewById(R.id.service_start);
        serviceClose = (Button) findViewById(R.id.service_close);
        final Intent intent = new Intent(this, VolumeService.class);

        bindService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bindService(intent, connection, Service.BIND_AUTO_CREATE);
            }
        });
        unbindService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unbindService(connection);
            }
        });
        serviceStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Service的count的值为:"
                        + binder.getCount(), Toast.LENGTH_SHORT).show();
            }
        });
        serviceStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(intent);
            }
        });
        serviceClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(intent);
            }
        });
        initView();
    }

    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e("tag", "service onServiceConnected");
            binder = (VolumeService.MyBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e("tag", "service onServiceDisconnected");
        }
    };

    private void initView() {

    }
}