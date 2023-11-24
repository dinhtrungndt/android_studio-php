package com.example.nguyendinhtrung_pk02294_asm.helpers;

import com.example.nguyendinhtrung_pk02294_asm.models.NewsModelResponse;
import com.example.nguyendinhtrung_pk02294_asm.models.UserLoginRequest;
import com.example.nguyendinhtrung_pk02294_asm.models.UserLoginResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface IRetrofitRouter {

    @POST("/login.php")
    Call<UserLoginResponse> login(@Body UserLoginRequest user);

    @GET("/get-news.php")
    Call<List<NewsModelResponse>> getNews();
}
