package com.mittas.goloopingmobilechallenge.api;

import com.mittas.goloopingmobilechallenge.data.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface UserService {

    @Headers("Content-Type: application/json")
    @POST("session/new")
    Call<User> getNewUser(@Body User newUser);



}
