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

    private void setUser(Resource<User> user) {
        observableUser.postValue(user);
    }

    public boolean isLoggedIn() {
        String defaultValue = resources.getString(R.string.is_logged_in_default);

        String isLoggedInString =  preferences.getString(resources.getString(R.string.is_logged_in_key), defaultValue);

        return Boolean.valueOf(isLoggedInString);
    }

    private void setLoggedIn(boolean isLoggedIn) {
        String isLoggedInString = String.valueOf(isLoggedIn);

        preferences
                .edit()
                .putString(resources.getString(R.string.is_logged_in_key), isLoggedInString)
                .apply();
    }

    /**
     * Using username and password, get back userid and token
     *
     * @param username
     * @param password
     */
    public void onLoginRequest(final String username, final String password) {
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

                    setLoggedIn(true);

                    loadUser();
                } else {
                    // Handle unsuccessful login
                    Resource<User> failedResponse = Resource.error("Couldn't login :(", null);
                    setUser(failedResponse);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                // Do nothing
            }
        });
    }

    /**
     * Using userid and token, get back email and avatar url
     */
    public void loadUser() {
               // Get userId from settings
        String defaultUserId = resources.getString(R.string.user_id_default);
        String userId = preferences.getString(resources.getString(R.string.user_id_key), defaultUserId);

        // Get token from settings
        String defaultToken = resources.getString(R.string.token_default);
        String token = preferences.getString(resources.getString(R.string.token_key), defaultToken);

        if((userId == defaultUserId) || (token == defaultToken)) {
            return;
        }

        service.getUserById(token, userId).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User user = response.body();

                    Resource<User> userResource = Resource.success(user);

                    setUser(userResource);
                } else {
                    // Handle unsuccessful user load
                    // Do nothing
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                // Do nothing
            }
        });
    }

    public void onAvatarChanged() {

    }


}
