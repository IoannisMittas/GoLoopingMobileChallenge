package com.mittas.goloopingmobilechallenge.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.mittas.goloopingmobilechallenge.BasicApp;
import com.mittas.goloopingmobilechallenge.UserRepository;
import com.mittas.goloopingmobilechallenge.data.User;
import com.mittas.goloopingmobilechallenge.vo.Resource;

public class ProfileViewModel extends AndroidViewModel {
    private final UserRepository repository;
    private final MediatorLiveData<Resource<User>> observableUser;

    public ProfileViewModel(@NonNull Application application) {
        super(application);

        repository = ((BasicApp) application).getRepository();

        observableUser = new MediatorLiveData<>();

        LiveData<Resource<User>> userInput = repository.getUser();

        // Observe the user from the repository and when it's updated forward the change
        observableUser.addSource(userInput, user -> observableUser.setValue(user));
    }

    public LiveData<Resource<User>> getUser() {
        return observableUser;
    }

    public void onNewAvatar(Uri imageUri) {
        repository.onNewAvatar(imageUri);
    }
}
