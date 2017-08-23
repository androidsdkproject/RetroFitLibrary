package com.example.android1.myapplication;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;

/**
 * Created by Android1 on 7/4/2017.
 */

public interface RetrofitArrayAPI {
    @GET("https://androidtutorialpoint.com/api/RetrofitAndroidArrayResponse")
    Call<List<Student>> getStudentDetails();
}
