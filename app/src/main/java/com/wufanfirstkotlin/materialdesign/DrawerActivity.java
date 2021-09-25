package com.wufanfirstkotlin.materialdesign;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.wufanfirstkotlin.BaseActivity;
import com.wufanfirstkotlin.R;

import org.jetbrains.annotations.NotNull;

/**
 * @author wf
 */
public class DrawerActivity extends BaseActivity {
    private DrawerLayout drawer;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        setSupportActionBar(findViewById(R.id.toolbar));

        drawer = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigation_view);

        navigationView.setCheckedItem(R.id.profile);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {

                drawer.closeDrawers();
                return true;
            }
        });

        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.open);
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