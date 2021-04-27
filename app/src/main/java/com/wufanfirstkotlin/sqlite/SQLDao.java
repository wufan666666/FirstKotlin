package com.wufanfirstkotlin.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

/**
 * 这个类用于数据库的增删改查
 *
 * @author : wf
 * @date : 2021年04月24日 10:30
 */
public class SQLDao {

    private Context context;
    private final MyDBOpenHelper myDBOpenHelper;

    public SQLDao(Context context) {
        this.context = context;
        myDBOpenHelper = new MyDBOpenHelper(context);
    }

    public void insert() {
        SQLiteDatabase database = myDBOpenHelper.getWritableDatabase();
        database.execSQL("insert into person (name,sex) values (?,?)", new Object[]{"wuFan", "男"});
        Toast.makeText(context,"插入内容成功",Toast.LENGTH_SHORT).show();
        database.close();
    }

    public void query() {
        SQLiteDatabase database = myDBOpenHelper.getWritableDatabase();
        String sql = "select * from  person where sex = '男'";
        Cursor cursor = database.rawQuery(sql, null);

        while (cursor.moveToNext()) {
            int personid = cursor.getInt(0); //获取第一列的值,第一列的索引从0开始
            String name = cursor.getString(1);//获取第二列的值
            Toast.makeText(context,name,Toast.LENGTH_SHORT).show();
        }

        cursor.close();
        database.close();
    }

    public void update() {
        SQLiteDatabase database = myDBOpenHelper.getWritableDatabase();
        database.execSQL("update  person set name ='wufan' where sex = '男'");
        Toast.makeText(context,"修改wuFan为wufan成功",Toast.LENGTH_SHORT).show();
        database.close();
    }

    public void delete() {
        SQLiteDatabase database = myDBOpenHelper.getWritableDatabase();
        database.execSQL("delete from person where sex = '男' ");
        Toast.makeText(context,"删除所有性别为男的数据成功",Toast.LENGTH_SHORT).show();
        database.close();
    }
}