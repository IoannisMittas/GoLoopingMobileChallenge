package com.mittas.goloopingmobilechallenge;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;

import com.mittas.goloopingmobilechallenge.api.UserService;
import com.mittas.goloopingmobilechallenge.data.User;

import org.json.JSONObject;

import retrofit2.Call;

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

    public boolean isLoggedIn() {
        boolean defaultValue = Boolean.valueOf(resources.getString(R.string.is_logged_in_default));
        return preferences.getBoolean(resources.getString(R.string.is_logged_in_key), defaultValue);
    }

    public void onLoginRequest(final String username, final String password) {
        // POST sessions/new  by giving username and password and get back userid and token
        User newUser = new User();
        newUser.setEmail(username);
        newUser.setPassword(password);




        // if successful
            // save token to sharedsettings

            // GET user/userid using token in header bearer, using userid and get back email and avatar url


            // launch profile activity with email and avatar_url got (inform the livedata: Resource<User> and
            // profile is observing)

        // else if unsuccessful
            // inform about the unsuccess somehow the login activity
    }

    public void onAvatarChanged() {

    }




}
