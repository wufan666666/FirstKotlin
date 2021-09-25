package com.wufanfirstkotlin.recycleview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;

import com.wufanfirstkotlin.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * @author C3415551
 */
public class RecycleViewActivity extends AppCompatActivity {

    private final ArrayList<String> names = new ArrayList<>();

    private RecyclerView recyclerView;

    private RecyclerViewAdapter adapter;

    private LinearLayoutManager linearLayoutManager;

    private ItemTouchHelper itemTouchHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view);
        initData();

        recyclerView = findViewById(R.id.recycle_view);

        linearLayoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);

        recyclerView.setLayoutManager(linearLayoutManager);


        adapter = new RecyclerViewAdapter(names,getApplicationContext());

        recyclerView.setAdapter(adapter);

        Callback callback = new Callback();
        itemTouchHelper  = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    private void initData() {
        names.add("宋江");
        names.add("卢俊义");
        names.add("吴用");
        names.add("公孙胜");
        names.add("关胜");
        names.add("林冲");
        names.add("秦明");
        names.add("呼延灼");
        names.add("花荣");
        names.add("柴进");
        names.add("李应");
        names.add("朱仝");
        names.add("鲁智深");
        names.add("武松");
        names.add("董平");
        names.add("张清");
        names.add("杨志");
        names.add("徐宁");
        names.add("索超");
    }
}