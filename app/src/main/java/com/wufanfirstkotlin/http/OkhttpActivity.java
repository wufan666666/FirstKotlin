package com.wufanfirstkotlin.http;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.wufanfirstkotlin.R;
import com.wufanfirstkotlin.himalaya.utils.L;
import com.wufanfirstkotlin.http.okhttp.IJsonDataListener;
import com.wufanfirstkotlin.http.okhttp.MNokHttp;
import com.wufanfirstkotlin.http.okhttp.ResponseClass;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

public class OkhttpActivity extends AppCompatActivity {

    private Button okhttp;
    private Button volley;
    private Button retrofit;
    protected Handler handler;
    private Button myhttp;

    public static final class InnerHandler extends Handler {
        private Callback callback;
        private final OkhttpActivity okhttpActivity;

        public InnerHandler(OkhttpActivity okhttpActivity) {
            this.callback = new SoftReference<Callback>(callback).get();
            this.okhttpActivity = okhttpActivity;
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                default:
                    String mes = msg.obj.toString();
                    Log.e("tag", "到了handle");
                    Log.e("tag", mes + "++++++");
                    Toast.makeText(okhttpActivity.getApplicationContext(), mes, Toast.LENGTH_LONG).show();
                    break;
            }
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);
        initView();
        this.handler = new InnerHandler(this);
        okhttp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //clickOKhttp();
                startActivity(new Intent(OkhttpActivity.this,TestOKHttpActivity.class));
            }
        });
        retrofit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickRetrofit();
            }
        });
        myhttp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequest();
            }
        });
        volley.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickVolley();
            }
        });
    }

    /**
     * 这里拿到的RequestQueue是一个请求队列对象，
     * 它可以缓存所有的 HTTP 请求，然后按照一定的算法并发地发出这些请求。RequestQueue 内部的设计就是非常合适高并发的，
     * 因此我们不必为每一次 HTTP 请求都创建一个RequestQueue对象，这是非常浪费资源的，基本上在每一个需要和网络交互的 Activity 中创建一个RequestQueue对象就足够了
     */
    private void clickVolley() {
        RequestQueue mQueue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest("https://www.baidu.com", new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                L.e("volley1", s);
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                L.e("volley2", volleyError.getMessage());
            }
        });
        //mQueue.add(stringRequest);

        StringRequest request = new StringRequest(com.android.volley.Request.Method.POST, "https://www.baidu.com",
                new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String s) {

            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap mm = new HashMap<String, String>();
                mm.put("param1", "value1");
                mm.put("param2", "value2");
                mm.put("param3", "value3");
                return mm;
            }
        };

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("https://www.tianqiapi.com/api/?version=v1",
                null, new com.android.volley.Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                System.out.println(jsonObject.toString());
                L.e("TAG",jsonObject.toString());
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });

        mQueue.add(jsonObjectRequest);
    }

    private void sendRequest() {
        MNokHttp.sendJsonRequest(null, "http://www.baidu.com", ResponseClass.class, new IJsonDataListener<ResponseClass>() {
            @Override
            public void onSuccess(ResponseClass m) {
                Log.e("tag", "访问成功" + (m == null));
            }

        });
    }

    private void clickRetrofit() {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        okHttpClientBuilder.connectTimeout(DEFAULT_KEYS_DIALER, TimeUnit.SECONDS);

        HttpLoggingInterceptor.Level level = HttpLoggingInterceptor.Level.BODY;
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(
                message -> Log.e("zbc", "Retrofit====Message:" + message));
        httpLoggingInterceptor.setLevel(level);
        okHttpClientBuilder.addInterceptor(httpLoggingInterceptor);

        OkHttpClient okHttpClient = okHttpClientBuilder.build();

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.apiopen.top/")
                .client(okHttpClient)
                .build();
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);

        retrofit2.Call<ResponseBody> video = retrofitService.getJoke(1, 1, "video");


        video.enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(retrofit2.Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {

            }

            @Override
            public void onFailure(retrofit2.Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    private void clickOKhttp() {
        /**
         *
         * 1、请求发送到哪里去了？
         *      请求发送到两个队列当中，1、等待中队列。2、运行中队列
         * 2、请求怎么被处理的？       先被加入到运行中队列，然后丢入线程池，直接执行getResponseWithInterceptorChain()
         *
         * 3、请求怎么被维护的？       每个请求被处理完之后，就会重新回到队列中，看看队列中是否有待处理的数据，有的话就交给线程池处理
         *
         */
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("page", 1);
            jsonObject.put("count", 1);
            jsonObject.put("type", "video");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        HttpLoggingInterceptor.Level level = HttpLoggingInterceptor.Level.BODY;
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(
                message -> Log.e("zbc", "Retrofit====Message:" + message));
        httpLoggingInterceptor.setLevel(level);
        //Log.e("jsonObjectStr==", jsonObject.toString());
        //RequestBody requestBody = new FormBody.Builder().add("page", "1").add("count", "1").add("type", "video").build();
        RequestBody requestBody = RequestBody.create(mediaType, String.valueOf(jsonObject));
        Request request = new Request.Builder().url("https://api.apiopen.top/getJoke").post(requestBody).build();
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor)
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();
        Message message = new Message();
        //Log.e("request==", request.toString());
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("onFailure", "failure");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //Log.e("onResponse", response.toString());
                int code = response.code();
                String data = response.body().string();
                Log.e("onResponse", data);
                Toast.makeText(OkhttpActivity.this,"response",Toast.LENGTH_SHORT).show();
                if (code == 200) {
                    message.what = 0x11;
                    message.obj = response.toString();
                    handler.sendMessage(message);
                }
            }
        });
    }

    private void initView() {
        okhttp = findViewById(R.id.okhttp);
        volley = findViewById(R.id.volley);
        retrofit = findViewById(R.id.retrofit);
        myhttp = findViewById(R.id.myhttp);
    }
}