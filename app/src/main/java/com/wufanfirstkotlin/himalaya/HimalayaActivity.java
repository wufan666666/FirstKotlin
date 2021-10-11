package com.wufanfirstkotlin.himalaya;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.wufanfirstkotlin.R;
import com.wufanfirstkotlin.himalaya.adapters.IndicatorAdapter;
import com.wufanfirstkotlin.himalaya.adapters.MainContentAdapter;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;

public class HimalayaActivity extends AppCompatActivity {
    private String TAG = "HimalayaActivity";
    private MagicIndicator magicIndicator;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_himalaya);
       initView();

    }

    private void initView() {
        magicIndicator = findViewById(R.id.main_indicator);
        magicIndicator.setBackgroundColor(ContextCompat.getColor(this,R.color.himalaya_color));
        //创建适配器
        IndicatorAdapter indicatorAdapter = new IndicatorAdapter(this);
        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setAdapter(indicatorAdapter);
        commonNavigator.setAdjustMode(true);

        indicatorAdapter.setOnIndicatorListener(new IndicatorAdapter.OnIndicatorListener() {
            @Override
            public void onTapClick(int position) {
                if (viewPager!=null){
                    viewPager.setCurrentItem(position);
                }
            }
        });

        viewPager = findViewById(R.id.content_viewpager);

        FragmentManager supportFragmentManager = getSupportFragmentManager();
        MainContentAdapter contentAdapter = new MainContentAdapter(supportFragmentManager);
        viewPager.setAdapter(contentAdapter);
        //将viewpager和indicator绑定在一起
        magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicator,viewPager);
    }
}