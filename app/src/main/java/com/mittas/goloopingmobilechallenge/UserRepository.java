package com.mittas.goloopingmobilechallenge;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.SharedPreferences;
import android.content.res.Resources;

import com.mittas.goloopingmobilechallenge.api.UserService;
import com.mittas.goloopingmobilechallenge.data.User;
import com.mittas.goloopingmobilechallenge.vo.Resource;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {
    private static UserRepository INSTANCE;
    private final UserService service;
    private final AppExecutors executors;
    private final SharedPreferences preferences;
    private final Resources resources;

    private final MutableLiveData<Resource<User>> observableUser;

    private UserRepository(UserService service, AppExecutors executors, SharedPreferences preferences, Resources resources) {
        this.service = service;
        this.executors = executors;
        this.preferences = preferences;
        this.resources = resources;

       observableUser = new MutableLiveData<>();
    }

    public static UserRepository getInstance(final UserService service, final AppExecutors executors, final SharedPreferences preferences, final Resources resources) {
        if (INSTANCE == null) {
            INSTANCE = new UserRepository(service, executors, preferences, resources);
        }
        return INSTANCE;
    }

    public LiveData<Resource<User>> getUser() {
        return observableUser;
    }

    public void setUser(Resource<User> user) {
        observableUser.postValue(user);
    }

    public boolean isLoggedIn() {
        boolean defaultValue = Boolean.valueOf(resources.getString(R.string.is_logged_in_default));
        return preferences.getBoolean(resources.getString(R.string.is_logged_in_key), defaultValue);
    }

    public void onLoginRequest(final String username, final String password) {
        // Give username and password, to get back userid and token
        User loginRequestUser = new User();
        loginRequestUser.setEmail(username);
        loginRequestUser.setPassword(password);

        service.getNewUser(loginRequestUser).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User newUser = response.body();
                    String userId = newUser.getUserId();
                    String token = newUser.getToken();

                    // Save userId
                    preferences
                            .edit()
                            .putString(resources.getString(R.string.user_id_key), userId)
                            .apply();

                    // Save token
                    preferences
                            .edit()
                            .putString(resources.getString(R.string.token_key), token)
                            .apply();

                    loadUser();
                } else {
                    // Handle unsuccessful login
                    Resource<User> failedResponse = Resource.error("Couldn't login. :(", null);
                    setUser(failedResponse);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                // TODO do something
            }
        });


    }

    public void loadUser() {
        // GET user/userid using token in header bearer, using userid and get back email and avatar url

        // get userId from settings


        // get token from settings



        service.getUserById(token, userId).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {


                    // launch profile activity with email and avatar_url got (inform the livedata: Resource<User> and
                    // profile is observing)

                } else {
                    // TODO do something
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

    public void onAvatarChanged() {

    }


}
