package com.mittas.goloopingmobilechallenge.ui.user;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mittas.goloopingmobilechallenge.R;
import com.mittas.goloopingmobilechallenge.util.Utility;
import com.mittas.goloopingmobilechallenge.viewmodel.LoginViewModel;

public class LoginActivity extends AppCompatActivity {
    private LoginViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        viewModel = ViewModelProviders.of(this).get(LoginViewModel.class);

        if(viewModel.isLoggedIn()) {
           // Start profile activity
            Intent intent = new Intent(this, ProfileActivity.class);
            startActivity(intent);
        } else {
            if (savedInstanceState == null) {
                LoginFragment fragment = new LoginFragment();
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragment_container, fragment, LoginFragment.TAG).commit();
            }
        }
    }



}
