package com.wufanfirstkotlin

import android.Manifest
import android.app.Activity
import android.content.ContentUris
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.RecyclerView
import com.wufanfirstkotlin.Service.ServiceIntentActivity
import com.wufanfirstkotlin.Service.ServiceJavaActivity
import com.wufanfirstkotlin.broadcast.BroadCastActivity
import com.wufanfirstkotlin.customView.CustomActivity
import com.wufanfirstkotlin.fragment.FragmentActivity
import com.wufanfirstkotlin.handler.HandlerActivity
import com.wufanfirstkotlin.himalaya.HimalayaActivity
import com.wufanfirstkotlin.http.OkhttpActivity
import com.wufanfirstkotlin.materialdesign.MaterialDesignActivity
import com.wufanfirstkotlin.mvp.LoginMVPActivity
import com.wufanfirstkotlin.recycleview.RecycleViewActivity
import com.wufanfirstkotlin.sqlite.MMKV.MmkvActivity
import com.wufanfirstkotlin.sqlite.SqliteActivity
import com.wufanfirstkotlin.sqlite.sharedpreference.SharedpreferenceActivity
import com.wufanfirstkotlin.viewPractice.CheckBoxActivity
import com.wufanfirstkotlin.viewPractice.DatePickerActivity
import com.wufanfirstkotlin.viewPractice.DialogActivity
import com.wufanfirstkotlin.viewPractice.WebViewActivity
import java.io.File
import java.io.InputStream


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var webviewbt: Button
    private lateinit var dialog: Button
    private lateinit var checkbox: Button
    private lateinit var datePicker: Button
    private lateinit var broadcast: Button
    private lateinit var service: Button
    private lateinit var intentservice: Button
    private lateinit var fragment: Button
    private lateinit var sqLite: Button
    private lateinit var http: Button
    private lateinit var custom_view: Button
    private lateinit var mvp_login: Button
    private lateinit var recycleView: Button
    private lateinit var handler: Button
    private lateinit var materialDesign: Button
    private lateinit var himalaya: Button
    private lateinit var SharedPreference: Button
    private lateinit var mmkv: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        webviewbt = findViewById(R.id.web_view_bt)
        dialog = findViewById(R.id.dialog)
        checkbox = findViewById(R.id.checkbox)
        datePicker = findViewById(R.id.date_picker)
        broadcast = findViewById(R.id.broadcast)
        service = findViewById(R.id.service)
        intentservice = findViewById(R.id.intent_service)
        fragment = findViewById(R.id.fragment)
        sqLite = findViewById(R.id.sqLite)
        http = findViewById(R.id.http)
        custom_view = findViewById(R.id.custom_view)
        mvp_login = findViewById(R.id.mvp_login)
        recycleView = findViewById(R.id.recycleView)
        handler = findViewById(R.id.handler)
        materialDesign = findViewById(R.id.materialDesign)
        himalaya = findViewById(R.id.himalaya)
        SharedPreference = findViewById(R.id.SharedPreference)
        mmkv = findViewById(R.id.mmkv)
        webviewbt.setOnClickListener(this)
        dialog.setOnClickListener(this)
        checkbox.setOnClickListener(this)
        datePicker.setOnClickListener(this)
        broadcast.setOnClickListener(this)
        service.setOnClickListener(this)
        intentservice.setOnClickListener(this)
        fragment.setOnClickListener(this)
        sqLite.setOnClickListener(this)
        http.setOnClickListener(this)
        custom_view.setOnClickListener(this)
        mvp_login.setOnClickListener(this)
        recycleView.setOnClickListener(this)
        handler.setOnClickListener(this)
        materialDesign.setOnClickListener(this)
        himalaya.setOnClickListener(this)
        SharedPreference.setOnClickListener(this)
        mmkv.setOnClickListener(this)
        testPermission(this)
    }

    private fun checkPermission(context: Context, checkList: Array<String>): List<String> {
        val list: MutableList<String> = ArrayList()
        for (i in checkList.indices) {
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(
                    context,
                    checkList[i]
                )
            ) {
                list.add(checkList[i])
            }
        }
        return list
    }

    //申请权限
    private fun requestPermission(activity: Activity, requestPermissionList: Array<String>) {
        //var requestPermissionList:Array<String> = arrayOf(requestPermissionList[1])
        ActivityCompat.requestPermissions(activity, requestPermissionList, 100)
    }

    //用户作出选择后，返回申请的结果
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode == 100) {
            for (i in permissions.indices) {
                if (permissions[i] == Manifest.permission.WRITE_EXTERNAL_STORAGE) {
                    if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(this@MainActivity, "存储权限申请成功", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@MainActivity, "存储权限申请失败", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                }
            }
        }
    }

    //测试申请存储权限
    private fun testPermission(activity: Activity) {
        val checkList = arrayOf(
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.INTERNET,
            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.ACCESS_WIFI_STATE,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,

            )
        val needRequestList = checkPermission(activity, checkList)
        if (needRequestList.isEmpty()) {
            //Toast.makeText(this@MainActivity, "无需申请权限", Toast.LENGTH_SHORT).show()
        } else {
            requestPermission(activity, needRequestList.toTypedArray())
            Log.e("tag", needRequestList.toString())
        }
    }


    /* private fun testShareMedia() {
         //获取目录：/storage/emulated/0/
         val rootFile: File = Environment.getExternalStorageDirectory()
         val imagePath: String =
             rootFile.getAbsolutePath() + File.separator + Environment.DIRECTORY_PICTURES + File.separator.toString() + "myPic.png"
         val bitmap = BitmapFactory.decodeFile(imagePath)
     }*/


    /*private fun getImagePath(context: Context) {
        val contentResolver = context.contentResolver
        val cursor: Cursor? = contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            null,
            null,
            null,
            null
        )
        if (cursor != null) {
            while (cursor.moveToNext()) {
                val imagePath: String =
                    cursor.getString(cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA))
                val bitmap = BitmapFactory.decodeFile(imagePath)
                break
            }
        }
    }


    private fun getImagePath2(context: Context) {
        val contentResolver = context.contentResolver
        val cursor = contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            null,
            null,
            null,
            null
        )
        while (cursor!!.moveToNext()) {
            //获取唯一的id
            val id = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.MediaColumns._ID))
            //通过id构造Uri
            val uri: Uri =
                ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id)
            openUri(uri)
            break
        }
    }*/

    /*private fun openUri(uri: Uri) {
        try {
            //从uri构造输入流
            val fis: InputStream? = contentResolver.openInputStream(uri)
            val bitmap = BitmapFactory.decodeStream(fis)
        } catch (e: Exception) {
        }
    }*/

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.checkbox -> startActivity(Intent(this, CheckBoxActivity::class.java))

            R.id.web_view_bt -> startActivity(Intent(this, WebViewActivity::class.java))

            R.id.dialog -> startActivity(Intent(this, DialogActivity::class.java))

            R.id.date_picker -> startActivity(Intent(this, DatePickerActivity::class.java))

            R.id.broadcast -> startActivity(Intent(this, BroadCastActivity::class.java))

            R.id.service -> startActivity(Intent(this, ServiceJavaActivity::class.java))

            R.id.intent_service -> startActivity(Intent(this, ServiceIntentActivity::class.java))

            R.id.fragment -> startActivity(Intent(this, FragmentActivity::class.java))

            R.id.sqLite -> startActivity(Intent(this, SqliteActivity::class.java))

            R.id.http -> startActivity(Intent(this, OkhttpActivity::class.java))

            R.id.custom_view -> startActivity(Intent(this, CustomActivity::class.java))

            R.id.mvp_login -> startActivity(Intent(this, LoginMVPActivity::class.java))

            R.id.recycleView -> startActivity(Intent(this, RecycleViewActivity::class.java))

            R.id.handler -> startActivity(Intent(this, HandlerActivity::class.java))

            R.id.materialDesign -> startActivity(Intent(this, MaterialDesignActivity::class.java))

            R.id.himalaya -> startActivity(Intent(this, HimalayaActivity::class.java))

            R.id.SharedPreference -> startActivity(Intent(this, SharedpreferenceActivity::class.java))

            R.id.mmkv -> startActivity(Intent(this, MmkvActivity::class.java))


        }
    }

}


