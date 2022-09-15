package com.fx.basics.a4_date_time;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.DatePicker;
import android.widget.Toast;

import com.fx.basics.R;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DateTimeActivity extends AppCompatActivity implements Chronometer.OnChronometerTickListener, DatePicker.OnDateChangedListener {

    @BindView(R.id.btnStart)
    Button btnStart;
    @BindView(R.id.btnStop)
    Button btnStop;
    @BindView(R.id.btnReset)
    Button btnReset;
    @BindView(R.id.btn_format)
    Button btn_format;
    @BindView(R.id.chronometer)
    Chronometer chronometer;
    @BindView(R.id.dp_test)
    DatePicker dp_test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_time);

        ButterKnife.bind(this);
        chronometer.setOnChronometerTickListener(this);
        Calendar calendar = Calendar.getInstance();
        int year=calendar.get(Calendar.YEAR);
        int monthOfYear=calendar.get(Calendar.MONTH);
        int dayOfMonth=calendar.get(Calendar.DAY_OF_MONTH);
        dp_test.init(year,monthOfYear,dayOfMonth,this);
    }

    @OnClick(R.id.btnStart)
    public void onclick1()   {
        chronometer.start();// 开始计时
    }

    @OnClick(R.id.btnStop)
    public void onclick2() {
        chronometer.stop();// 结束计时
    }

    @OnClick(R.id.btnReset)
    public void onclick3() {
        chronometer.setBase(SystemClock.elapsedRealtime());// // 复位
    }

    @OnClick(R.id.btn_format)
    public void onclick4() {
        chronometer.setFormat("Time：%s");// 开始计时
    }


    @Override
    public void onChronometerTick(Chronometer chronometer) {
        String time = chronometer.getText().toString();
        if (time.equals("00:00")) {
            Toast.makeText(DateTimeActivity.this, "时间到了~", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        Toast.makeText(DateTimeActivity.this,"您选择的日期是："+year+"年"+(monthOfYear+1)+"月"+dayOfMonth+"日!",Toast.LENGTH_SHORT).show();
    }
}