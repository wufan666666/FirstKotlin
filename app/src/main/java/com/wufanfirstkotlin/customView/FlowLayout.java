package com.wufanfirstkotlin.customView;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : wf
 * @date : 2021年06月15日 16:07
 */
public class FlowLayout extends ViewGroup {

    /**
     * @author wf
     * @date 2021/6/15 17:06
     * @param null
     * @return null
     * @description 每一行的行高
     */
    List<Integer> listLineHeight = new ArrayList<>();
    /**
     * @author wf
     * @date 2021/6/15 17:06
     * @param null
     * @return null
     * @description 所有的控件的集合
     */
    List<List<View>> listLineView = new ArrayList<>();

    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new MarginLayoutParams(getContext(), attributeSet);
    }

    /**
     * @param widthMeasureSpec  上层控件传过来的测量规则
     * @param heightMeasureSpec
     * @author wf
     * @date 2021/6/15 16:14
     * @description 测量group自己的大小 每个控件的布局都不是独立的,都会参照父布局的大小
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        //父控件对这个控件期望的高宽
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        //当前FlowLayout中的子控件得到的宽高等.
        int selfWidth = 0;
        int selfHeight = 0;

        //当前FlowLayout中的子控件已经使用了的宽高等.
        int countWidth = 0;
        int countHeight = 0;

        //存储每一个子控件的宽高等.
        int iChildWidth = 0;
        int iChildHeight = 0;

        //每一行的子控件的集合
        List<View> viewList = new ArrayList<>();
        //获取到当前布局中子控件的总数
        int childCount = getChildCount();

        for (int x = 0; x < childCount; x++) {
            //获取子控件
            View childAt = getChildAt(x);
            measureChild(childAt, widthMeasureSpec, heightMeasureSpec);
            MarginLayoutParams layoutParams = (MarginLayoutParams) childAt.getLayoutParams();
            iChildWidth = childAt.getMeasuredWidth() + layoutParams.leftMargin
                    + layoutParams.rightMargin;

            iChildHeight = childAt.getMeasuredHeight() + layoutParams.topMargin
                    + layoutParams.bottomMargin;
            //判断是否需要换行
            if (countHeight + iChildHeight > heightSize) {

                selfWidth = Math.max(countWidth, selfWidth);
                selfHeight = countHeight + selfHeight;
                listLineHeight.add(countHeight);
                listLineView.add(viewList);
                countWidth = iChildWidth;
                countHeight = iChildHeight;
                viewList.clear();
                viewList.add(childAt);
            } else {//不需要换行
                countWidth = countWidth + iChildWidth;
                countHeight = Math.max(countHeight, iChildHeight);
                viewList.add(childAt);
            }

            //针对最后一行
            if (x == childCount - 1) {
                selfWidth = Math.max(countWidth, selfWidth);
                selfHeight = countHeight + selfHeight;
                listLineView.add(viewList);
                listLineHeight.add(countHeight);
            }

        }
        int measureWidth = widthMode == MeasureSpec.EXACTLY ? widthSize : selfWidth;
        int measureHeight = heightMode == MeasureSpec.EXACTLY ? heightSize : selfHeight;

        //这个方法就是将测量出来的最终结果保存
        setMeasuredDimension(measureWidth, measureHeight);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int left,right,top,bottom;
        int countLeft = 0;
        int countTop = 0;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }


}