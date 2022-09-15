package com.fx.basics.a2_imageview;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.fx.basics.R;

public class ImageViewUpgradeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view_upgrade);
        RoundImageView roundImageView = findViewById(R.id.imageView6);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.sea);
        roundImageView.setBitmap(bitmap);
    }
}