package com.mittas.goloopingmobilechallenge;

import com.mittas.goloopingmobilechallenge.api.UserService;

public class UserRepository {
    private static UserRepository INSTANCE;
    private final UserService service;
    private final AppExecutors executors;

    private UserRepository(UserService service, AppExecutors executors) {
        this.service = service;
        this.executors = executors;
    }

    public static UserRepository getInstance(final UserService service, final AppExecutors executors) {
        if (INSTANCE == null) {
            INSTANCE = new UserRepository(service, executors);
        }
        return INSTANCE;
    }

    public void onLoginRequest(final String username, final String password) {

    }

}
