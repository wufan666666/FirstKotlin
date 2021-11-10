package com.wf.lifecycle.databinding.editView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.wf.lifecycle.R;
import com.wf.lifecycle.databinding.ActivityEditviewBinding;

/**
 * @author wf
 */
public class EditviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityEditviewBinding editviewBinding = DataBindingUtil.setContentView(this, R.layout.activity_editview);
        editviewBinding.setUserName(new SimpleEditViewModel());
    }
}