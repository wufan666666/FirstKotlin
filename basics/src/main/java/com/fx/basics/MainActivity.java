package com.fx.basics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.fx.basics.a1_button.ButtonActivity;
import com.fx.basics.a1_textview.TextViewBasicsActivity;
import com.fx.basics.a1_textview.TextViewUpgradeActivity;
import com.fx.basics.a2_editview.EditViewBasicActivity;
import com.fx.basics.a2_imageview.ImageViewBasicActivity;
import com.fx.basics.a2_imageview.ImageViewUpgradeActivity;
import com.fx.basics.a2_radiobutton_checkbox.RadioButtonActivity;
import com.fx.basics.a3_listview.basic.ListViewBasicActivity;
import com.fx.basics.a3_listview.upgrade.ListViewUpgradeActivity;
import com.fx.basics.a3_scrollview.ScrollViewActivity;
import com.fx.basics.a4_date_time.DateTimeActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity /*implements View.OnClickListener*/ {
    @BindView(R.id.btnOne)
    Button btnOne;
    @BindView(R.id.btnTwo)
    Button btnTwo;
    @BindView(R.id.btnThree)
    Button btnThree;
    @BindView(R.id.btnFour)
    Button btnFour;
    @BindView(R.id.btnFive)
    Button btnFive;
    @BindView(R.id.btnSix)
    Button btnSix;
    @BindView(R.id.btnSeven)
    Button btnSeven;
    @BindView(R.id.btnEight)
    Button btnEight;
    @BindView(R.id.btnNine)
    Button btnNine;
    @BindView(R.id.btnTen)
    Button btnTen;
    @BindView(R.id.btnEleven)
    Button btnEleven;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }
    @OnClick(R.id.btnOne)
    public void onViewClick1() {
        startActivity(ButtonActivity.class);
    }

    @OnClick(R.id.btnTwo)
    public void onViewClick2() {
        startActivity(TextViewBasicsActivity.class);
    }

    @OnClick(R.id.btnThree)
    public void onViewClick3() {
        startActivity(TextViewUpgradeActivity.class);
    }

    @OnClick(R.id.btnFour)
    public void onViewClick4() {
        startActivity(EditViewBasicActivity.class);
    }

    @OnClick(R.id.btnFive)
    public void onViewClick5() {
        startActivity(ImageViewBasicActivity.class);
    }

    @OnClick(R.id.btnSix)
    public void onViewClick6() {
        startActivity(ImageViewUpgradeActivity.class);
    }

    @OnClick(R.id.btnSeven)
    public void onViewClick7() {
        startActivity(RadioButtonActivity.class);
    }

    @OnClick(R.id.btnEight)
    public void onViewClick8() {
        startActivity(ListViewBasicActivity.class);
    }

    @OnClick(R.id.btnNine)
    public void onViewClick9() {
        startActivity(ListViewUpgradeActivity.class);
    }

    @OnClick(R.id.btnTen)
    public void onViewClick10() {
        startActivity(ScrollViewActivity.class);
    }

    @OnClick(R.id.btnEleven)
    public void onViewClick11() {
        startActivity(DateTimeActivity.class);
    }

    public void startActivity(Class clzss) {
        startActivity(new Intent(this, clzss));
    }
}