package com.example.android1.myapplication;

import retrofit.Call;
import retrofit.http.GET;

/**
 * Created by Android1 on 7/4/2017.
 */

public interface RetrofitObjectAPI {
    @GET("https://androidtutorialpoint.com/api/RetrofitAndroidObjectResponse")
    Call<Student> getStudentDetails();
}
