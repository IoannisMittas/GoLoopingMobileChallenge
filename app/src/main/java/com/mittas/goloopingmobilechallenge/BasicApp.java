package com.mittas.goloopingmobilechallenge;

import android.app.Application;

import com.mittas.starwarswiki.api.SwapiService;
import com.mittas.starwarswiki.api.SwapiServiceHolder;
import com.mittas.starwarswiki.data.LocalDatabase;


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

    public SwapiService getSwapiService() {
        return SwapiServiceHolder.getInstance().getSwapiService();
    }

    public Repository getRepository() {
        return Repository.getInstance(getSwapiService(), appExecutors);
    }
}
