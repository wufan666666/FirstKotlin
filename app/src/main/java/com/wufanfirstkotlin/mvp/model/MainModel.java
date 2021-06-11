package com.wufanfirstkotlin.mvp.model;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;


/**
 * @author : wf
 * @date : 2021年06月08日 16:35
 */
public class MainModel implements IModel {

    @Override
    public void login(String username, String password, final Callback callback) {
        new Handler(Looper.getMainLooper()).postDelayed(() -> {

            if (username.equals("123")  && password.equals("123") ) {
                callback.onSuccess(new User(username, password));

            } else {

                callback.onFailure("账号密码错误");
            }
        }, 2000);
    }
}