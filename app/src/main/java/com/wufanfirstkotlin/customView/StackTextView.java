package com.wufanfirstkotlin.customView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.wufanfirstkotlin.R;

/**
 * @author : wf
 * @date : 2021年12月02日 16:34
 */
public class StackTextView extends androidx.appcompat.widget.AppCompatTextView {

    private Paint changePaint;
    private Paint originPaint;
    private float mProgress = 0.36f;

    public StackTextView(Context context) {
        this(context, null);
    }

    public StackTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StackTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    private Paint setPaintSetting(int color) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(color);
        paint.setDither(true);
        paint.setTextSize(getTextSize());
        return paint;
    }

    private void initView(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.StackTextView);

        int ChangeColor = typedArray.getColor(R.styleable.StackTextView_changeColor, getTextColors().getDefaultColor());
        int OriginColor = typedArray.getColor(R.styleable.StackTextView_originColor, getTextColors().getDefaultColor());

        changePaint = setPaintSetting(ChangeColor);
        originPaint = setPaintSetting(OriginColor);
        typedArray.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.save();
        int middle = (int) (mProgress * getWidth());


        String text = getText().toString();
        Rect rect = new Rect(0, 0, middle, getHeight());

        canvas.clipRect(rect);
        originPaint.getTextBounds(text, 0, text.length(), rect);
        int x = getWidth() / 2 - rect.width() / 2;
        Paint.FontMetrics fontMetrics = originPaint.getFontMetrics(); 
        int dy = (int) ((fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom);
        int baseline = getHeight() / 2 + dy;
        canvas.drawText(text, x, baseline, originPaint);
        canvas.restore();

        canvas.save();
        rect = new Rect(middle, 0, getWidth(), getHeight());
        canvas.clipRect(rect);
        canvas.drawText(text, x, baseline, changePaint);
        canvas.restore();
    }
}