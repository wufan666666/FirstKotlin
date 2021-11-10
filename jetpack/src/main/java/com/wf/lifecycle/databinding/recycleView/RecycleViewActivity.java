package com.wf.lifecycle.databinding.recycleView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.wf.lifecycle.R;
import com.wf.lifecycle.databinding.ActivityRecycleviewBinding;

/**
 * @author wf
 */
public class RecycleViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityRecycleviewBinding recycleviewBinding = DataBindingUtil.setContentView(this, R.layout.activity_recycleview);
        CatAdapter catAdapter = new CatAdapter(CatList.getCatList());
        recycleviewBinding.recycleview.setLayoutManager(new LinearLayoutManager(this));
        recycleviewBinding.recycleview.setAdapter(catAdapter);
    }
}