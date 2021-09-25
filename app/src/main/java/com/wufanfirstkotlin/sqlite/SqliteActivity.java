package com.wufanfirstkotlin.sqlite;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.wufanfirstkotlin.R;

/**
 * @author : wf
 * @date : 2021年04月23日 16:13
 */
public class SqliteActivity extends AppCompatActivity {

    private Button add;
    private Button delete;
    private Button update;
    private Button select;

    /**
     * 1、写一个类去继承SQLiteOpenHelper
     * 2、实现里面的方法，创建构造方法
     * 3、创建这个子类的对象， 在调用getWritableDatabase（），即可创建数据库
     * SQL：CREATE database databaseName{character set 类型,...}
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
        Log.e("tag", "onCreate-SqliteActivity");
        MyDBOpenHelper myDBOpenHelper = new MyDBOpenHelper(this);
        myDBOpenHelper.getWritableDatabase();
        initView();
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLDao sqlDao =new SQLDao(getApplicationContext());
                sqlDao.insert();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLDao sqlDao =new SQLDao(getApplicationContext());
                sqlDao.delete();
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLDao sqlDao =new SQLDao(getApplicationContext());
                sqlDao.update();
            }
        });
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLDao sqlDao =new SQLDao(getApplicationContext());
                sqlDao.query();
            }
        });
    }

    private void initView() {
        add = findViewById(R.id.add);
        delete = findViewById(R.id.delete);
        update = findViewById(R.id.update);
        select = findViewById(R.id.select);
    }
}