package com.mittas.goloopingmobilechallenge;

import android.app.Application;

import com.mittas.goloopingmobilechallenge.api.UserService;
import com.mittas.goloopingmobilechallenge.api.UserServiceHolder;


/**
 * Android Application class. Used for accessing singletons.
 */
public class BasicApp extends Application {

    private AppExecutors appExecutors;

    @Override
    public void onCreate() {
        super.onCreate();

        appExecutors = new AppExecutors();
    }

    public UserService getUserService() {
        return UserServiceHolder.getInstance().getUserService();
    }

    public UserRepository getRepository() {
        return UserRepository.getInstance(getUserService(), appExecutors);
    }
}
