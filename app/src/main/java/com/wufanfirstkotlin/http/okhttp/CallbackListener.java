package com.wufanfirstkotlin.http.okhttp;

import java.io.InputStream;

/**
 * @author : wf
 * @date : 2021年04月29日 15:12
 */
public interface CallbackListener {

    void onSuccess(InputStream inputStream);

}