package com.wufanfirstkotlin.http;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.wufanfirstkotlin.R;
import com.wufanfirstkotlin.http.okhttp.IJsonDataListener;
import com.wufanfirstkotlin.http.okhttp.MNokHttp;
import com.wufanfirstkotlin.http.okhttp.ResponseClass;

import java.io.IOException;
import java.lang.ref.SoftReference;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkhttpActivity extends AppCompatActivity {

    private Button okhttp;
    private Button volley;
    private Button retrofit;
    protected Handler handler;
    private Button myhttp;

    public static final class InnerHandler extends Handler {
        private Callback callback;
        private OkhttpActivity okhttpActivity;

        public InnerHandler(OkhttpActivity okhttpActivity) {
            this.callback = new SoftReference<Callback>(callback).get();
            this.okhttpActivity = okhttpActivity;
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case 0x11:
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
                clickOKhttp();
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
    }

    private void sendRequest() {
        MNokHttp.sendJsonRequest(null, "http://www.baidu.com", ResponseClass.class, new IJsonDataListener<ResponseClass>() {
            @Override
            public void onSuccess(ResponseClass m) {
                Log.e("tag","访问成功"+(m==null));
            }

        });
    }

    private void clickRetrofit() {

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
        Request request = new Request.Builder().url("http://www.baidu.com").build();
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        Message message = new Message();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("tag", "failure");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e("tag", response.toString());
                int code = response.code();
                if (code == 200) {
                    message.what = 0x11;
                    message.obj = response.toString();
                    handler.sendMessage(message);
                }
            }
        });
    }

    private void initView() {
        okhttp = (Button) findViewById(R.id.okhttp);
        volley = (Button) findViewById(R.id.volley);
        retrofit = (Button) findViewById(R.id.retrofit);
        myhttp = (Button) findViewById(R.id.myhttp);
    }
}