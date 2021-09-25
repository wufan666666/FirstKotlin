package com.wufanfirstkotlin.fragment;

import android.os.Bundle;
import android.util.Log;
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

import java.util.LinkedList;
import java.util.List;

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
    private MyFragment fg1;
    private MyFragment1 fg2;
    private MyFragment2 fg3;
    private MyFragment3 fg4;
    private FragmentManager fgManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_fragment);
        Log.e("activty","onCreate"+"--activity");
        fgManager = getSupportFragmentManager();
        initView();
        alert.setOnClickListener(this);
        message.setOnClickListener(this);
        information.setOnClickListener(this);
        setting.setOnClickListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("activty","onStart"+"--activity");
        alert.performClick();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("activty","onResume"+"--activity");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("activty","onStop"+"--activity");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("activty","onPause"+"--activity");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("activty","onDestroy"+"--activity");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("tag","onRestart"+"--activity");
    }

    private void initView() {
        lyTop = findViewById(R.id.ly_top);
        topText = findViewById(R.id.top_text);
        lyContent = findViewById(R.id.ly_content);
        buttonView = findViewById(R.id.button_view);
        lyButton = findViewById(R.id.ly_button);
        alert = findViewById(R.id.alert);
        message = findViewById(R.id.message);
        information = findViewById(R.id.information);
        setting = findViewById(R.id.setting);
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
                    fg2 = new MyFragment1("second fragment");
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
                    fg3 = new MyFragment2("third fragment");
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
                    fg4 = new MyFragment3("forth fragment");
                    fragmentTransaction.add(R.id.ly_content, fg4);
                } else {
                    fragmentTransaction.show(fg4);
                }
                break;
        }
        fragmentTransaction.commit();
    }
}