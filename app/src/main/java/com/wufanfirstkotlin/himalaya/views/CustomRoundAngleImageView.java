package com.wufanfirstkotlin.himalaya.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.appcompat.widget.AppCompatImageView;

/**
 * @author : wf
 * @date : 2021年10月11日 14:46
 */
public class CustomRoundAngleImageView extends AppCompatImageView {
    float width, height;

    public CustomRoundAngleImageView(Context context) {
        this(context, null);
        init(context, null);
    }

    public CustomRoundAngleImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        init(context, attrs);
    }

    public CustomRoundAngleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (Build.VERSION.SDK_INT < 18) {
            setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        width = getWidth();
        height = getHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (width >= 32 && height > 32) {
            Path path = new Path();
            //四个圆角
            path.moveTo(32, 0);
            path.lineTo(width - 32, 0);
            path.quadTo(width, 0, width, 32);
            path.lineTo(width, height - 32);
            path.quadTo(width, height, width - 32, height);
            path.lineTo(32, height);
            path.quadTo(0, height, 0, height - 32);
            path.lineTo(0, 32);
            path.quadTo(0, 0, 32, 0);

            canvas.clipPath(path);
        }
        super.onDraw(canvas);
    }

}