package com.wufanfirstkotlin.customView;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.wufanfirstkotlin.R;

/**
 * @author C3415551
 */
public class CustomActivity extends AppCompatActivity implements View.OnClickListener {

    private Button title_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);
        title_text = findViewById(R.id.text_title);
        title_text.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent= new Intent();
        intent.setAction("com.wf.action.sa");
        startActivity(intent);
    }
}