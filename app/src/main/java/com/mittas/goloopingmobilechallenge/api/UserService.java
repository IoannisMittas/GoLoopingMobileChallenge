package com.mittas.goloopingmobilechallenge.api;

import com.mittas.goloopingmobilechallenge.data.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserService {

    @Headers("Content-Type: application/json")
    @POST("session/new")
    Call<User> getNewUser(@Body User loginRequestUser);

    @Headers("Content-Type: application/json")
    @GET("user/{userid}")
    Call<User> getUserById(@Header("Bearer") String token, @Path("userid") String userId);




}
