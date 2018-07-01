package com.mittas.goloopingmobilechallenge.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserServiceHolder {
    private static UserServiceHolder INSTANCE;

    private UserService service;

    private static final String BASE_URL = "localhost:3000/";

    private UserServiceHolder() {
        service = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(UserService.class);
    }

    public static UserServiceHolder getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserServiceHolder();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    public UserService getUserService() {
        return service;
    }
}
