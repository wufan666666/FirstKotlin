package com.wufanfirstkotlin.http.okhttp;

import android.os.Handler;
import android.os.Looper;

import com.alibaba.fastjson.JSON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


/**
 * @author : wf
 * @date : 2021年04月29日 15:52
 */
public class JsonCallbackListener<T>  implements CallbackListener{
    private Class<T> responseClass;
    private IJsonDataListener iJsonDataListener;

    private Handler mHandler = new Handler(Looper.getMainLooper());

    public JsonCallbackListener(Class<T> responseClass,IJsonDataListener iJsonDataListener){
        this.responseClass = responseClass;
        this.iJsonDataListener= iJsonDataListener;
    }

    @Override
    public void onSuccess(InputStream inputStream) {
        String response = getContent(inputStream);
        T clazz = JSON.parseObject(response,responseClass);
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                iJsonDataListener.onSuccess(clazz);
            }
        });
    }

    private String getContent(InputStream inputStream) {
        String content = null;
        try {


        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while (true) {
            try {
                if (!((line = reader.readLine()) !=null)){
                    sb.append(line + "\n");
                }
            } catch (IOException e) {
                System.out.println("Error= "+e.toString());
            }finally {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    System.out.println("Error= "+e.toString());
                }
            }
            return sb.toString();
        }
        }catch (Exception e){
            e.printStackTrace();
        }
        return content;
    }
}