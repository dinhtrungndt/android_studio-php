package com.example.nguyendinhtrung_pk02294_asm.helpers;

import com.example.nguyendinhtrung_pk02294_asm.models.LichHocModelResponse;
import com.example.nguyendinhtrung_pk02294_asm.models.ModelPutUser;
import com.example.nguyendinhtrung_pk02294_asm.models.ModelResetPass;
import com.example.nguyendinhtrung_pk02294_asm.models.MonHocModelResponse;
import com.example.nguyendinhtrung_pk02294_asm.models.NewsModelResponse;
import com.example.nguyendinhtrung_pk02294_asm.models.TranscriptsModelResponse;
import com.example.nguyendinhtrung_pk02294_asm.models.UserLoginRequest;
import com.example.nguyendinhtrung_pk02294_asm.models.UserLoginResponse;
import com.example.nguyendinhtrung_pk02294_asm.models.UserModelResponse;
import com.example.nguyendinhtrung_pk02294_asm.models.UserRegister;
import com.example.nguyendinhtrung_pk02294_asm.models.UserRegisterRequest;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface IRetrofitRouter {

    @POST("/login.php")
    Call<UserLoginResponse> login(@Body UserLoginRequest user);

    @POST("/register.php")
    Call<UserLoginResponse> register(@Body UserRegisterRequest user);

    @GET("/get-news.php")
    Call<List<NewsModelResponse>> getNews();

    @GET("/get-news-detail.php")
    Call<NewsModelResponse> getNewsDetail(@Query("id") int id);

    @GET("/get-lichhoc.php")
    Call<List<LichHocModelResponse>> getLichHoc();

    @GET("/get-monhoc.php")
    Call<List<MonHocModelResponse>> getMonHoc();

    @GET("/get-users.php")
    Call<List<UserModelResponse>> getUser();

    @GET("/get-transcripts.php")
    Call<List<TranscriptsModelResponse>> getTranscripts();

    @POST("/forgot-password.php")
    Call<UserRegister> forgotPassword(@Body ModelResetPass user);

    @PUT("/update_user_ad.php")
    Call<UserLoginResponse> putUser(@Body ModelPutUser user);

}
