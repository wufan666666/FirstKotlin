package com.wf.lifecycle.databinding.simple;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.wf.lifecycle.R;
import com.wf.lifecycle.databinding.ActivityDatabindingBinding;

/**
 * @author wf
 */
public class DatabindingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityDatabindingBinding databindingBinding = DataBindingUtil.setContentView(this, R.layout.activity_databinding);
        Cat cat = new Cat("姓名：古力娜扎", 5);
        databindingBinding.setCat(cat);
        databindingBinding.setOnEventClick(new OnEventOnclick(this));
    }
}