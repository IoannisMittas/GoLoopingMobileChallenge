package com.mittas.goloopingmobilechallenge;

import android.app.Application;
import android.content.SharedPreferences;
import android.content.res.Resources;

import com.mittas.goloopingmobilechallenge.api.UserService;
import com.mittas.goloopingmobilechallenge.api.UserServiceHolder;


/**
 * Android Application class. Used for accessing singletons.
 */
public class BasicApp extends Application {
    public static final String PREFS_NAME = "PreferencesFile";
    private SharedPreferences preferences;
    private Resources resources;
    private AppExecutors appExecutors;

    @Override
    public void onCreate() {
        super.onCreate();

        preferences =  this.getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        resources = this.getResources();
        appExecutors = new AppExecutors();
    }

    public UserService getUserService() {
        return UserServiceHolder.getInstance().getUserService();
    }

    public UserRepository getRepository() {
        return UserRepository.getInstance(getUserService(), appExecutors, preferences, resources);
    }
}
