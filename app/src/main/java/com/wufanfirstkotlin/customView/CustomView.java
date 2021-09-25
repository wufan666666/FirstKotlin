package com.wufanfirstkotlin.customView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : wf
 * @date : 2021年05月24日 10:16
 * 继承于view ViewGroup（涉及到事件分发）
 * 系统没有的效果（ImageView,button,TextView等）
 */
public class CustomView extends ViewGroup {
    private final int mHorizontalSpacing = dp2px(16);
    private final int mVerticalSpacing = dp2px(8);


    /**
     * 一行一行的存储，用于layout
     */
    List<List<View>> allLines = new ArrayList<>();

    /**
     * 记录每一行的行高，用于layout
     */
    List<Integer> lineHeights = new ArrayList<>();


    public CustomView(Context context) {
        super(context);
    }

    /**
     * 在xml中布局时调用这个
     * 通过这个构造函数,我们可以使用反射来获取XMl中的属性
     * @param context
     * @param attrs
     */
    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 在主题中使用时
     *
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 第一步
     * 自定义view的测量方法
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //获取宽高的一种模式，布局的宽高都是由这个方法测量，
        int width = MeasureSpec.getMode(widthMeasureSpec);
        int height = MeasureSpec.getMode(heightMeasureSpec);
        /*//在布局中指定了  wrap_content
        if (width==MeasureSpec.AT_MOST){

        }
        // 在布局中指定了确切的值 100dp match parent 最大不能超过父布局
        if (width==MeasureSpec.EXACTLY){

        }
        //在布局中尽可能的大   listView ScrollView
        if (width==MeasureSpec.UNSPECIFIED){

        }*/
        int paddingTop = getPaddingTop();
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();

        int selfWidth = MeasureSpec.getSize(widthMeasureSpec);
        int selfHeight = MeasureSpec.getSize(heightMeasureSpec);

        //保存一行中所有的view
        List<View> lineView = new ArrayList<>();

        //记录这行已经使用了多宽的size
        int lineWidthUsed = 0;
        //一行的行高
        int lineHeight = 0;

        int parentNeedWidth = 0;
        int parentNeedHeight = 0;


        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            LayoutParams layoutParams = childView.getLayoutParams();

            int childWidthMeasureSpec = getChildMeasureSpec(widthMeasureSpec, paddingLeft + paddingRight, layoutParams.width);
            int childHeightMeasureSpec = getChildMeasureSpec(heightMeasureSpec, paddingTop + paddingBottom, layoutParams.height);

            childView.measure(childWidthMeasureSpec, childHeightMeasureSpec);

            //获取子view的宽高
            int childMeasureWidth = childView.getMeasuredWidth();
            int childMeasureHeight = childView.getMeasuredHeight();


            //通过宽高来判断是否需要换行，通过换行后的每行的行高来获取整个viewGroup的行高
            if (childMeasureWidth + lineWidthUsed + mHorizontalSpacing > selfWidth) {
                allLines.add(lineView);
                lineHeights.add(lineHeight);

                parentNeedHeight = parentNeedWidth + lineHeight + mVerticalSpacing;

                parentNeedWidth = Math.max(parentNeedWidth, lineWidthUsed + mHorizontalSpacing);


                //一旦换行，我们就可以判断当前行需要的宽和高了，所以此时要记录下来
                lineView = new ArrayList<>();
                lineHeight = 0;
                lineWidthUsed = 0;


            }

            //view是分行layout，所以要记录每一行有哪些view，这样可以方便layout布局
            lineView.add(childView);
            //每行都会有自己的宽高
            lineHeight = Math.max(lineHeight, childMeasureHeight);
            lineWidthUsed = lineWidthUsed + childMeasureWidth + mHorizontalSpacing;


        }
        //根据子view的度量结果，来重新度量自己viewGroup
        //作为一个ViewGroup，他自己也是一个view，他的大小也需要根据他的父亲给他提供的宽高来度量
        int widthSpec = MeasureSpec.getMode(widthMeasureSpec);
        int heightSpec = MeasureSpec.getMode(heightMeasureSpec);

        int realWidth = (widthSpec == MeasureSpec.EXACTLY) ? selfWidth : parentNeedWidth;
        int realHeight = (heightSpec == MeasureSpec.EXACTLY) ? selfHeight : parentNeedHeight;


        //度量自己
        setMeasuredDimension(realWidth, realHeight);
    }

    /**
     * 第二步
     *
     * @param changed
     * @param l
     * @param t
     * @param r
     * @param b
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int curL = getPaddingLeft();
        int curT = getPaddingTop();
        int lineCount = allLines.size();
        for (int i = 0; i < lineCount; i++) {
            List<View> lineView = allLines.get(i);
            int lineHeight = lineHeights.get(i);
            for (int j = 0; j < lineView.size(); j++) {
                View view = lineView.get(j);
                int left = curL;
                int top = curT;
                int right = left + view.getMeasuredWidth();
                int bottom = left + view.getMeasuredHeight();
                view.layout(left, top, right, bottom);
                curL = right + mHorizontalSpacing;
            }
            curL = getPaddingLeft();
            curT = curT + lineHeight + mVerticalSpacing;
        }
    }

    /**
     * 第三步
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    /**
     * @param dp
     * @return int
     * @author wf
     * @date 2021/5/25 10:44
     * @description
     */
    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp, Resources.getSystem().getDisplayMetrics());

    }

    /**
     *
     * @param dps
     */
    public void setRetrofit(int dps) {
        Log.e("retrofit", dps + "");
    }


}