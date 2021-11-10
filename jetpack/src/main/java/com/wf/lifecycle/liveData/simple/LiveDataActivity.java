package com.wf.lifecycle.liveData.simple;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.TextView;

import com.wf.lifecycle.R;

import java.util.Timer;
import java.util.TimerTask;


/**
 * @author wf
 * liveData 实现的是界面的数据变化更新，确保没有内存泄露，数据始终保持最新状态
 * 如果liveData中的数据发生变化，会通知界面产生更新
 */
public class LiveDataActivity extends AppCompatActivity {

    private TextView mTextView;
    private MyViewModel mMyViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_data);
        mTextView = findViewById(R.id.textView);

        mMyViewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(MyViewModel.class);

        mTextView.setText(String.valueOf(mMyViewModel.getCurrentSecond().getValue()));
        mMyViewModel.getCurrentSecond().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                mTextView.setText(String.valueOf(integer));
            }
        });
        startTime();
    }

    private void startTime() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                mMyViewModel.getCurrentSecond().postValue(mMyViewModel.getCurrentSecond().getValue()+1);
            }
        },1000,1000);
    }
}