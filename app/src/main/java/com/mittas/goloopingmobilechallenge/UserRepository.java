package com.mittas.goloopingmobilechallenge;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;

import com.mittas.goloopingmobilechallenge.api.UserService;

public class UserRepository {
    private static UserRepository INSTANCE;
    private final UserService service;
    private final AppExecutors executors;
    private final SharedPreferences preferences;
    private final Resources resources;

    private UserRepository(UserService service, AppExecutors executors, SharedPreferences preferences, Resources resources) {
        this.service = service;
        this.executors = executors;
        this.preferences = preferences;
        this.resources = resources;
    }

    public static UserRepository getInstance(final UserService service, final AppExecutors executors, final SharedPreferences preferences, final Resources resources) {
        if (INSTANCE == null) {
            INSTANCE = new UserRepository(service, executors, preferences, resources);
        }
        return INSTANCE;
    }

    public void onLoginRequest(final String username, final String password) {

    }

    public boolean isLoggedIn() {
        boolean defaultValue = Boolean.valueOf(resources.getString(R.string.is_logged_in_default));
        return preferences.getBoolean(resources.getString(R.string.is_logged_in_key), defaultValue);
    }

}
