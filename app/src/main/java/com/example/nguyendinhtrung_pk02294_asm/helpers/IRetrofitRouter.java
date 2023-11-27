package com.example.nguyendinhtrung_pk02294_asm.helpers;

import com.example.nguyendinhtrung_pk02294_asm.models.LichHocModelResponse;
import com.example.nguyendinhtrung_pk02294_asm.models.MonHocModelResponse;
import com.example.nguyendinhtrung_pk02294_asm.models.NewsModelResponse;
import com.example.nguyendinhtrung_pk02294_asm.models.TranscriptsModelResponse;
import com.example.nguyendinhtrung_pk02294_asm.models.UserLoginRequest;
import com.example.nguyendinhtrung_pk02294_asm.models.UserLoginResponse;
import com.example.nguyendinhtrung_pk02294_asm.models.UserModelResponse;
import com.example.nguyendinhtrung_pk02294_asm.models.UserRegisterRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface IRetrofitRouter {

    @POST("/login.php")
    Call<UserLoginResponse> login(@Body UserLoginRequest user);

    @POST("/register.php")
    Call<UserLoginResponse> register(@Body UserRegisterRequest user);

    @GET("/get-news.php")
    Call<List<NewsModelResponse>> getNews();

    @GET("/get-lichhoc.php")
    Call<List<LichHocModelResponse>> getLichHoc();

    @GET("/get-monhoc.php")
    Call<List<MonHocModelResponse>> getMonHoc();

    @GET("/get-users.php")
    Call<List<UserModelResponse>> getUser();

    @GET("/get-transcripts.php")
    Call<List<TranscriptsModelResponse>> getTranscripts();

    @FormUrlEncoded
    @POST("/forgot-password.php")
    Call<Void> forgotPassword(@Field("email") String email);
}
