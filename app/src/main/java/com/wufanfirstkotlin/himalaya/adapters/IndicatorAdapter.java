package com.wufanfirstkotlin.himalaya.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

import com.wufanfirstkotlin.R;

import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

/**
 * @author : wf
 * @date : 2021年10月09日 15:02
 */
public class IndicatorAdapter extends CommonNavigatorAdapter {
    private Context context;
    private final String[] array;
    private OnIndicatorListener onIndicatorListener;

    public IndicatorAdapter(Context context) {
        this.context = context;
        this.array = context.getResources().getStringArray(R.array.indicator_title);
    }

    @Override
    public int getCount() {
        if (array != null) {
            return array.length;
        } else {
            return 0;
        }
    }

    @Override
    public IPagerTitleView getTitleView(Context context, int index) {
        SimplePagerTitleView simplePagerTitleView = new SimplePagerTitleView(context);
        simplePagerTitleView.setNormalColor(Color.GRAY);
        simplePagerTitleView.setSelectedColor(Color.WHITE);
        simplePagerTitleView.setText(array[index]);
        simplePagerTitleView.setTextSize(18);
        simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onIndicatorListener != null) {
                    onIndicatorListener.onTapClick(index);
                }
            }
        });
        return simplePagerTitleView;
    }

    @Override
    public IPagerIndicator getIndicator(Context context) {
        LinePagerIndicator linePagerIndicator= new LinePagerIndicator(context);
        linePagerIndicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
        linePagerIndicator.setColors(Color.WHITE);
        return linePagerIndicator;
    }

    public void setOnIndicatorListener(OnIndicatorListener listener){
        this.onIndicatorListener = listener;
    }
    public  interface OnIndicatorListener{
        /**
         * 点击title
         * @param position 位置
         */
        void onTapClick(int position);
    }
}