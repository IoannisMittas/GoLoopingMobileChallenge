package com.mittas.goloopingmobilechallenge.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.mittas.goloopingmobilechallenge.BasicApp;
import com.mittas.goloopingmobilechallenge.UserRepository;

public class LoginViewModel extends AndroidViewModel{
    private final UserRepository repository;

    public LoginViewModel(@NonNull Application application) {
        super(application);

        repository = ((BasicApp) application).getRepository();
    }

    public void onLoginRequest(final String username, final String password) {
        repository.onLoginRequest(username, password);
    }
}


