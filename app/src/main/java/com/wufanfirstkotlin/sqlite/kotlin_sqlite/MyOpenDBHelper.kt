package com.wufanfirstkotlin.sqlite.kotlin_sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.wufanfirstkotlin.himalaya.utils.L


class MyOpenDBHelper(context: Context?) : SQLiteOpenHelper(context, "${Constants.TABLE_NAME}", null, Constants.VERSION) {
    var TAG = "MyOpenDBHelper"

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("Create table employee (id integer primary key AUTOINCREMENT not null,age INT,name TEXT)")
        L.e(TAG, "创建数据表employee")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        L.e(TAG, "更新数据表employee")
        when (oldVersion) {
            1 -> {
                db?.execSQL("Alter table  employee  add address Text")
                db?.execSQL("Alter table  employee  add description Text")
            }
            2 -> {
                db?.execSQL("Alter table  employee  add description Text")
            }
            3 -> {

            }
        }
    }

}