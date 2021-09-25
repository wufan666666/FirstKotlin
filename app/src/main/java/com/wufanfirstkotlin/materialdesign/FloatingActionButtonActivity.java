package com.wufanfirstkotlin.materialdesign;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.wufanfirstkotlin.BaseActivity;
import com.wufanfirstkotlin.R;
import com.wufanfirstkotlin.materialdesign.bean.CatBean;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Random;

public class FloatingActionButtonActivity extends BaseActivity {

    private DrawerLayout drawer;
    private NavigationView navigationView;
    private FloatingActionButton float_button;
    private RecyclerView recyclerView;
    private final ArrayList<CatBean> catBeanArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floating_action_button);
        setSupportActionBar(findViewById(R.id.toolbar));

        drawer = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigation_view);
        float_button = findViewById(R.id.float_button);
        recyclerView = findViewById(R.id.recycle_view_cat);

        navigationView.setCheckedItem(R.id.profile);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {

                drawer.closeDrawers();
                return true;
            }
        });
        initData();

        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.open);
        }

        CatAdapter catAdapter = new CatAdapter(this, catBeanArrayList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(catAdapter);
        float_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,"欢迎来到喵星人世界！！！",Snackbar.LENGTH_SHORT).setAction("更多", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        toast("欢迎来到喵星人世界！！！喵星有你更精彩！");
                    }
                }).show();
            }
        });
    }

    private void initData() {
        CatBean []catBeans = {
                new CatBean("乖乖",R.mipmap.cat1),
                new CatBean("六一",R.mipmap.cat2),
                new CatBean("皮皮",R.mipmap.cat3),
                new CatBean("猪猪",R.mipmap.cat4),
                new CatBean("西巴",R.mipmap.cat5),
                new CatBean("尼奥",R.mipmap.cat6),
                new CatBean("初晴",R.mipmap.cat7),
                new CatBean("清迈",R.mipmap.cat8),
                new CatBean("倪菲",R.mipmap.cat9),
                new CatBean("十几",R.mipmap.cat10),
                new CatBean("楠楠",R.mipmap.cat11),
                new CatBean("灵笼",R.mipmap.cat12)
        };
        catBeanArrayList.clear();
        for (int i = 0; i < 100; i++) {
            Random random = new Random();
            int index = random.nextInt(catBeans.length);
            catBeanArrayList.add(catBeans[index]);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.backup:
                toast("点击了backup");
                break;
            case R.id.setting:
                toast("点击了setting");
                break;
            case R.id.delete:
                toast("点击了delete");
                break;
            case android.R.id.home:
                drawer.openDrawer(GravityCompat.START);
                break;
            default:
        }
        return true;
    }
}