package com.wufanfirstkotlin.himalaya.views;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

import com.wufanfirstkotlin.R;

/**
 * @author : wf
 * @date : 2021年10月14日 15:30
 */
public class LoadingView extends AppCompatImageView {
    private float rotateDegree = 0.0f;

    public LoadingView(Context context) {
        this(context, null);
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //setImageResource(R.mipmap.onload);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.rotate(rotateDegree, getWidth() / 2, getHeight() / 2);
        super.onDraw(canvas);
    }
}