package com.fx.basics.a1_textview;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.TextView;

import com.fx.basics.R;

public class TextViewBasicsActivity extends AppCompatActivity {

    private TextView button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view);
        button = findViewById(R.id.text_android);
        Drawable[] compoundDrawables = button.getCompoundDrawables();
        compoundDrawables[1].setBounds(100,0,200,200);
        button.setCompoundDrawables(compoundDrawables[0],compoundDrawables[1],compoundDrawables[2],compoundDrawables[3]);
    }
}