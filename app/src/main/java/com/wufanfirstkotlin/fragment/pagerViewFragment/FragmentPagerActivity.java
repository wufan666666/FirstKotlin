package com.wufanfirstkotlin.fragment.pagerViewFragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.wufanfirstkotlin.R;

/**
 * @author : wf
 * @date : 2021年04月15日 14:43
 */
public class FragmentPagerActivity extends AppCompatActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {

    private LinearLayout lyTop;
    private TextView topText;
    private ViewPager lyContent;
    private View buttonView;
    private LinearLayout lyButton;
    private TextView alert;
    private TextView message;
    private TextView information;
    private TextView setting;
    private MyFragmentPagerAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_fragment_pager);
        Log.e("tag", "onCreate" + "--activity");
        adapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        initView();
        alert.setOnClickListener(this);
        message.setOnClickListener(this);
        information.setOnClickListener(this);
        setting.setOnClickListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("tag", "onStart" + "--activity");
        alert.performClick();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("tag", "onResume" + "--activity");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("tag", "onStop" + "--activity");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("tag", "onPause" + "--activity");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("tag", "onDestroy" + "--activity");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("tag", "onRestart" + "--activity");
    }

    private void initView() {
        lyTop = (LinearLayout) findViewById(R.id.ly_top);
        topText = (TextView) findViewById(R.id.top_text);
        lyContent = findViewById(R.id.ly_content);
        buttonView = (View) findViewById(R.id.button_view);
        lyButton = (LinearLayout) findViewById(R.id.ly_button);
        alert = (TextView) findViewById(R.id.alert);
        message = (TextView) findViewById(R.id.message);
        information = (TextView) findViewById(R.id.information);
        setting = (TextView) findViewById(R.id.setting);
        lyContent.setAdapter(adapter);
        lyContent.setCurrentItem(0);
        lyContent.addOnPageChangeListener(this);
    }

    //重置所有文本的选中状态
    private void setSelected() {
        alert.setSelected(false);
        message.setSelected(false);
        information.setSelected(false);
        setting.setSelected(false);
    }

    //隐藏所有Fragment
    /*private void hideAllFragment(FragmentTransaction fragmentTransaction) {
        if (fg1 != null) fragmentTransaction.hide(fg1);
        if (fg2 != null) fragmentTransaction.hide(fg2);
        if (fg3 != null) fragmentTransaction.hide(fg3);
        if (fg4 != null) fragmentTransaction.hide(fg4);
    }*/

    @Override
    public void onClick(View v) {
        //hideAllFragment(fragmentTransaction);
        switch (v.getId()) {
            case R.id.alert:
                setSelected();
                lyContent.setCurrentItem(0);
                topText.setText(alert.getText());
                alert.setSelected(true);

                break;

            case R.id.message:
                setSelected();
                message.setSelected(true);
                lyContent.setCurrentItem(1);
                topText.setText(message.getText());

                break;

            case R.id.information:
                setSelected();
                topText.setText(information.getText());
                lyContent.setCurrentItem(2);
                information.setSelected(true);

                break;

            case R.id.setting:
                setSelected();
                topText.setText(setting.getText());
                lyContent.setCurrentItem(3);
                setting.setSelected(true);

                break;
        }
        //fragmentTransaction.commit();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

        if (state == 2) {
            switch (lyContent.getCurrentItem()) {
                case 0:
                    setSelected();
                    topText.setText(alert.getText());
                    alert.setSelected(true);
                    break;
                case 1:
                    setSelected();
                    topText.setText(message.getText());
                    message.setSelected(true);
                    break;
                case 2:
                    setSelected();
                    topText.setText(information.getText());
                    information.setSelected(true);
                    break;
                case 3:
                    setSelected();
                    topText.setText(setting.getText());
                    setting.setSelected(true);
                    break;
            }
        }
    }

}