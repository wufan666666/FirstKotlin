package com.wufanfirstkotlin.sqlite.kotlin_sqlite

import android.content.ContentValues
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.contentValuesOf
import com.wufanfirstkotlin.BaseActivity
import com.wufanfirstkotlin.R
import com.wufanfirstkotlin.himalaya.utils.L

class SqliteKotlinActivity : BaseActivity() {
    var TAG = "SqliteKotlinActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sqlite)
        var sqlLiteHelper = MyOpenDBHelper(this)
        var db = sqlLiteHelper.writableDatabase

        var add = findViewById<Button>(R.id.add)
        var delete = findViewById<Button>(R.id.delete)
        var update = findViewById<Button>(R.id.update)
        var query = findViewById<Button>(R.id.select)

        add.setOnClickListener {
            L.e(TAG, "插入语句执行")
            var value = ContentValues().apply {
                put("age", 11)
                put("name", "章珊")
                put("address", "四川省成都市郫都区")
            }
            db.insert("employee", null, value)
        }

        delete.setOnClickListener {
            L.e(TAG, "删除语句执行")
            db.delete("employee", "id = ?", arrayOf("1"))
        }

        update.setOnClickListener {
            L.e(TAG, "更新语句执行")
            var values = ContentValues()
            values.put("address", "四川省成都市温江区")

            db?.update("employee", values, "id = ?", arrayOf("2"))
        }

        query.setOnClickListener {
            L.e(TAG, "查询语句执行")
            var cursor = db?.query("employee", null, null, null, null, null, "id")

            if (cursor!!.moveToFirst()) {
                do {
                    var id = cursor?.getInt(cursor.getColumnIndex("id"))
                    var age = cursor?.getInt(cursor.getColumnIndex("age"))
                    var name = cursor?.getString(cursor.getColumnIndex("name"))
                    var address = cursor?.getString(cursor.getColumnIndex("address"))
                    L.e(TAG, "employee id is $id")
                    L.e(TAG, "employee age is $age")
                    L.e(TAG, "employee name is $name")
                    L.e(TAG, "employee address is $address")
                    toast("employee id is $id " +
                            "\n employee age is $age" +
                            "\n employee name is $name" +
                            "\n employee address is $address")
                } while (cursor?.moveToNext())
            }

            cursor?.close()


        }
    }
}