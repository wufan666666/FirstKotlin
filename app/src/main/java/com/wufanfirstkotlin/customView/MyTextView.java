package com.wufanfirstkotlin.customView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.wufanfirstkotlin.R;

/**
 * @author : wf
 * @date : 2021年11月29日 15:30
 */
public class MyTextView extends LinearLayout {

    private String mText;
    private int mColor;
    private int mTextSize;
    private Paint mPaint;
    //private int mPadding;

    public MyTextView(Context context) {
        this(context, null);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyTextView);

        mText = typedArray.getString(R.styleable.MyTextView_jamesText);
        mColor = typedArray.getColor(R.styleable.MyTextView_jamesTextColor, Color.RED);
        //mPadding = typedArray.getDimensionPixelSize(R.styleable.MyTextView_jamesPadding, 0);
        mTextSize = typedArray.getDimensionPixelSize(R.styleable.MyTextView_jamesTextSize, px2sp(60));

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(mColor);
        mPaint.setTextSize(mTextSize);

        //回收
        typedArray.recycle();
    }

    private int px2sp(int sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, getResources().getDisplayMetrics());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        if (widthMode == MeasureSpec.AT_MOST) {

            Rect rect = new Rect();
            mPaint.getTextBounds(mText, 0, mText.length(), rect);
            width = rect.width() ;
        }
        if (heightMode == MeasureSpec.AT_MOST) {
            Rect rect = new Rect();
            mPaint.getTextBounds(mText, 0, mText.length(), rect);
            height = rect.height() ;
        }

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
        float dy = (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom;
        float baseline = getHeight() / 2 + dy;

        canvas.drawText(mText, 0, baseline, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}