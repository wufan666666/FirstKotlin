package com.wufanfirstkotlin.http;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * @author : wf
 * @date : 2021年06月08日 10:38
 */
public interface RetrofitService {

    /**
     * @param page 1
     * @param count 1
     * @param type 1
     * @return
     */
    @POST("getJoke")
    @FormUrlEncoded
    Call<ResponseBody> getJoke(@Field("page") int page, @Field("count") int count,@Field("type")String type);
}