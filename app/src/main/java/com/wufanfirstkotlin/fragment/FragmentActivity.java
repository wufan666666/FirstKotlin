package com.wufanfirstkotlin.fragment;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.wufanfirstkotlin.R;

/**
 * @author : wf
 * @date : 2021年04月15日 14:43
 */
public class FragmentActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout lyTop;
    private TextView topText;
    private FrameLayout lyContent;
    private View buttonView;
    private LinearLayout lyButton;
    private TextView alert;
    private TextView message;
    private TextView information;
    private TextView setting;
    private MyFragment fg1, fg2, fg3, fg4;
    private FragmentManager fgManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_fragment);
        fgManager = getSupportFragmentManager();
        initView();
        alert.performClick();
        alert.setOnClickListener(this);
        message.setOnClickListener(this);
        information.setOnClickListener(this);
        setting.setOnClickListener(this);
    }

    private void initView() {
        lyTop = (LinearLayout) findViewById(R.id.ly_top);
        topText = (TextView) findViewById(R.id.top_text);
        lyContent = (FrameLayout) findViewById(R.id.ly_content);
        buttonView = (View) findViewById(R.id.button_view);
        lyButton = (LinearLayout) findViewById(R.id.ly_button);
        alert = (TextView) findViewById(R.id.alert);
        message = (TextView) findViewById(R.id.message);
        information = (TextView) findViewById(R.id.information);
        setting = (TextView) findViewById(R.id.setting);
    }

    //重置所有文本的选中状态
    private void setSelected() {
        alert.setSelected(false);
        message.setSelected(false);
        information.setSelected(false);
        setting.setSelected(false);
    }

    //隐藏所有Fragment
    private void hideAllFragment(FragmentTransaction fragmentTransaction) {
        if (fg1 != null) fragmentTransaction.hide(fg1);
        if (fg2 != null) fragmentTransaction.hide(fg2);
        if (fg3 != null) fragmentTransaction.hide(fg3);
        if (fg4 != null) fragmentTransaction.hide(fg4);
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction fragmentTransaction = fgManager.beginTransaction();
        hideAllFragment(fragmentTransaction);
        switch (v.getId()) {
            case R.id.alert:
                setSelected();

                topText.setText(alert.getText());
                alert.setSelected(true);
                if (fg1 == null) {
                    fg1 = new MyFragment("first fragment");
                    fragmentTransaction.add(R.id.ly_content, fg1);
                } else {
                    fragmentTransaction.show(fg1);
                }
                break;

            case R.id.message:
                setSelected();
                message.setSelected(true);
                topText.setText(message.getText());
                if (fg2 == null) {
                    fg2 = new MyFragment("second fragment");
                    fragmentTransaction.add(R.id.ly_content, fg2);
                } else {
                    fragmentTransaction.show(fg2);
                }
                break;

            case R.id.information:
                setSelected();
                topText.setText(information.getText());
                information.setSelected(true);
                if (fg3 == null) {
                    fg3 = new MyFragment("third fragment");
                    fragmentTransaction.add(R.id.ly_content, fg3);
                } else {
                    fragmentTransaction.show(fg3);
                }
                break;

            case R.id.setting:
                setSelected();
                topText.setText(setting.getText());
                setting.setSelected(true);
                if (fg4 == null) {
                    fg4 = new MyFragment("forth fragment");
                    fragmentTransaction.add(R.id.ly_content, fg4);
                } else {
                    fragmentTransaction.show(fg4);
                }
                break;
        }
        fragmentTransaction.commit();
    }
}