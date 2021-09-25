package com.wufanfirstkotlin.materialdesign;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.wufanfirstkotlin.R;

/**
 * @author C3415551
 */
public class MaterialDesignActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView toolbar;
    private TextView drawer;
    private TextView float_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_design);

        initView();
        initListener();
    }



    private void initListener() {
        toolbar.setOnClickListener(this);
        drawer.setOnClickListener(this);
        float_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar:
                getStartActivity(ToolbarActivity.class);
                break;
            case R.id.drawer:
                getStartActivity(DrawerActivity.class);
                break;
            case R.id.float_button:
                getStartActivity(FloatingActionButtonActivity.class);
                break;
            default:
                getStartActivity(MaterialDesignActivity.class);
        }
    }


    public void getStartActivity(Class<? extends AppCompatActivity> clz) {
        startActivity(new Intent(this, clz));
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        drawer =  findViewById(R.id.drawer);
        float_button =  findViewById(R.id.float_button);
    }
}