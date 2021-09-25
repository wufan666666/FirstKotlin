package com.wufanfirstkotlin.materialdesign;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.wufanfirstkotlin.R;


/**
 * @author wf
 * 问题待解决：statusbar上滑，置顶为灰色
 */
public class CatDetailActivity extends AppCompatActivity {
    /**
     * 猫名
     */
    public static final String CAT_NAME = "cat_name";

    /**
     * 猫图片 ID
     */
    public static final String CAT_IMAGE_ID = "cat_image_id";
    private AppBarLayout appBarLayout;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private ImageView catImageView;
    private Toolbar toolbar;
    private TextView catTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_detail);

        initView();
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        String catName = getIntent().getStringExtra(CatDetailActivity.CAT_NAME);
        int catImage = getIntent().getIntExtra(CatDetailActivity.CAT_IMAGE_ID, 0);
        collapsingToolbarLayout.setTitle(catName);
        Glide.with(this).load(catImage).into(catImageView);

        catTextView.setText("俄罗斯蓝猫（Russian Blue），历史上曾被称做阿契安吉蓝猫，曾有过三种不同的名字。最初是阿契安吉蓝猫，直到20世纪40年代才有现在的名字，另外有段时间它则叫做马耳他猫。证据显示，这种猫确实原产于俄罗斯，因为已在俄罗斯寒带地区发现了同种的猫。俄罗斯蓝猫体型细长，大而直立的尖耳朵，脚掌小而圆，走路像是用脚尖在走。身上披着银蓝色光泽的短被毛，配上修长苗条的体型和轻盈的步态，尽显一派猫中的贵族风度。");
        getWindow().setStatusBarColor(Color.parseColor("#00000000"));

    }


    private void initView() {
        appBarLayout = findViewById(R.id.app_bar_layout);
        collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar_layout);
        catImageView = findViewById(R.id.cat_image_view);
        toolbar = findViewById(R.id.toolbar);
        catTextView = findViewById(R.id.cat_text_view);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}