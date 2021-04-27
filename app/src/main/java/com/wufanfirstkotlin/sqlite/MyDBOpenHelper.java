package com.wufanfirstkotlin.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


/**
 * @author : wf
 * @date : 2021年04月23日 15:09
 */
public class MyDBOpenHelper extends SQLiteOpenHelper {

    public MyDBOpenHelper(Context context){
        super(context,"person.db",null,2);
        Log.e("tag","create database");
    }

    /**
     * 数据库第一次创建时被调用
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE person(personid INTEGER PRIMARY KEY AUTOINCREMENT,name VARCHAR(20))");
        Log.e("tag","create table");
    }

    /**
     * 软件版本号发生改变时被调用
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("alter table person add sex varchar(4)");
        Log.e("tag","update table");
    }
}