package com.wufanfirstkotlin.sqlite.kotlin_sqlite

import android.content.Context

class Dao(context: Context) {
    var openDBHelper:MyOpenDBHelper = MyOpenDBHelper(context)

    fun insert(age:Int,name:String,address:String){
        var writableDatabase = openDBHelper.writableDatabase

        writableDatabase.execSQL("Insert into employee (age,name,address) values (${age},${name},${address})")
    }
}