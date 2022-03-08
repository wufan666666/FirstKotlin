package com.wufanfirstkotlin.customView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.View.MeasureSpec;

import androidx.annotation.Nullable;

import com.wufanfirstkotlin.R;

/**
 * @author : wf
 * @date : 2021年12月01日 11:28
 */
public class QQStepView extends View {

    private String mStepText;
    private int mInnerColor;
    private int mOuterColor;
    private int mTextColor;
    private int mBetweenSize;
    private int mTextSize;
    private int mInnerProgress;

    public QQStepView(Context context) {
        this(context, null);
    }

    public QQStepView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public QQStepView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.QQStepView);
        mStepText = typedArray.getString(R.styleable.QQStepView_stepText);
        mInnerColor = typedArray.getColor(R.styleable.QQStepView_stepInnerColor, Color.RED);
        mOuterColor = typedArray.getColor(R.styleable.QQStepView_stepOuterColor, Color.BLUE);
        mTextColor = typedArray.getColor(R.styleable.QQStepView_stepTextColor, Color.BLACK);
        mBetweenSize = typedArray.getDimensionPixelSize(R.styleable.QQStepView_stepBetweenCircle, px2sp(15));
        mTextSize = typedArray.getDimensionPixelSize(R.styleable.QQStepView_stepTextSize, px2sp(15));
        mInnerProgress = typedArray.getInt(R.styleable.QQStepView_stepInnerProcess, 0);


        typedArray.recycle();
    }

    private int px2sp(int sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, getResources().getDisplayMetrics());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);


        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);


        setMeasuredDimension(width > height ? height : width, height > width ? width : height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(mOuterColor);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(mBetweenSize);
        RectF rectF = new RectF(mBetweenSize / 2, mBetweenSize / 2, getWidth() - mBetweenSize / 2, getHeight() - mBetweenSize / 2);
        canvas.drawArc(rectF, 135, 270, false, paint);


        Paint innerPaint = new Paint();
        innerPaint.setStyle(Paint.Style.STROKE);
        innerPaint.setStrokeCap(Paint.Cap.ROUND);
        innerPaint.setStrokeWidth(mBetweenSize);
        innerPaint.setColor(mInnerColor);
        canvas.drawArc(rectF, 135, 270 * mInnerProgress / 100, false, innerPaint);


        Paint textPaint = new Paint();
        textPaint.setColor(mTextColor);
        textPaint.setTextSize(mTextSize);
        textPaint.setAntiAlias(true);
        Rect rect = new Rect();
        textPaint.getTextBounds(mStepText, 0, mStepText.length(), rect);
        int dx = getWidth() / 2 - rect.width() / 2;
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        float dy = (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom;
        float baseline = getHeight() / 2 + dy;

        canvas.drawText(mStepText, dx, baseline, textPaint);
    }

    public synchronized void setStepMax(int max) {
        this.mInnerProgress = max;
        invalidate();
    }
}