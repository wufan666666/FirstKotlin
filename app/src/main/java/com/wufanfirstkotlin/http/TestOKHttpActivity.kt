package com.wufanfirstkotlin.http

import android.os.Bundle
import android.widget.Button
import com.wufanfirstkotlin.BaseActivity
import com.wufanfirstkotlin.R
import com.wufanfirstkotlin.himalaya.utils.L
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class TestOKHttpActivity : BaseActivity() {
    lateinit var get: Button
    lateinit var post: Button
    lateinit var synpost: Button
    lateinit var synget: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_okhttp_test)
        get = findViewById<Button>(R.id.get)
        post = findViewById<Button>(R.id.post)
        synpost = findViewById<Button>(R.id.synpost)
        synget = findViewById<Button>(R.id.synget)
        get.setOnClickListener { clickGet() }
        post.setOnClickListener { clickPost() }
        synget.setOnClickListener { clicksynGet() }
        synpost.setOnClickListener { clicksynPost() }
    }
    //执行异步的get方法
    private fun clicksynGet() {
        var client = OkHttpClient()
        var url = "https://api.apiopen.top/getJoke"
        var requset = Request.Builder().url(url).build()

        var newCall = client.newCall(requset)

        newCall.enqueue(object: Callback{
            override fun onFailure(call: Call, e: IOException) {
                L.e("TAG",e.toString())
            }

            override fun onResponse(call: Call, response: Response) {
                runOnUiThread {
                    toast("成功访问"+response.toString())
                }
            }

        })

    }

    //执行异步的post方法
    private fun clicksynPost() {
        val jsonObject = JSONObject()

        jsonObject.put("page", 1)
        jsonObject.put("count", 1)
        jsonObject.put("type", "video")

        var client = OkHttpClient()
        var url = "https://api.apiopen.top/getJoke"
        var requestBody = RequestBody.create(MediaType.get("application/json; charset=utf-8"), jsonObject.toString())
        var requset = Request.Builder().url(url).post(requestBody).build()

        var newCall = client.newCall(requset)

        newCall.enqueue(object: Callback{
            override fun onFailure(call: Call, e: IOException) {
                L.e("TAG",e.toString())
            }

            override fun onResponse(call: Call, response: Response) {
                runOnUiThread {
                    toast("成功访问"+response.toString())
                }
            }

        })
    }

    private fun clickPost() {
        Thread() {
            kotlin.run {
                val jsonObject = JSONObject()

                jsonObject.put("page", 1)
                jsonObject.put("count", 1)
                jsonObject.put("type", "video")

                var client = OkHttpClient()
                var url = "https://api.apiopen.top/getJoke"
                var requestBody = RequestBody.create(MediaType.get("application/json; charset=utf-8"), jsonObject.toString())
                var request = Request.Builder()
                    .post(requestBody)
                    .url(url)
                    .build()

                var response = client.newCall(request).execute()
                L.e("TAG", response.toString())
            }
        }.start()
    }

    //get请求，利用同步方法execute执行，必须在异步线程中执行
    fun clickGet() {
        //新建一个OkHttpClient对象
        Thread() {
            kotlin.run {
                var client = OkHttpClient()
                var url = "https://www.baidu.com"
                //创建一个request对象
                var request = Request.Builder()
                    .url(url)
                    .get()
                    .build();
                //获取响应并把响应体返回
                var response = client.newCall(request).execute()

                L.e("TAG", response?.toString())
            }
        }.start()

    }
}