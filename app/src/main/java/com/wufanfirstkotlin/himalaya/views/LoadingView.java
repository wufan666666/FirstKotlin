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
    /**
     * 旋转的角度
     */
    private float rotateDegree = 0.0f;
    private boolean needRotate;

    public LoadingView(Context context) {
        this(context, null);
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setImageResource(R.mipmap.onload);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        needRotate = true;
        //绑定到window的时候
        post(new Runnable() {
            @Override
            public void run() {
                rotateDegree += 30;
                rotateDegree = rotateDegree <= 360 ? rotateDegree : 0;
                invalidate();
                //判断是否继续旋转
                if (needRotate == true) {
                    postDelayed(this, 50);
                }
            }
        });
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        //从window中解绑
        needRotate = false;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.rotate(rotateDegree, getWidth() / 2, getHeight() / 2);
        super.onDraw(canvas);
    }
}