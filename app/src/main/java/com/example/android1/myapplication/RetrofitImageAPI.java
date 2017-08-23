package com.example.android1.myapplication;

/**
 * Created by Android1 on 7/4/2017.
 */

import com.squareup.okhttp.ResponseBody;

import retrofit.Call;
import retrofit.http.GET;

public interface RetrofitImageAPI {
    @GET("https://androidtutorialpoint.com/api/RetrofitAndroidImageResponse")
    Call<ResponseBody>getImageDetails();
}