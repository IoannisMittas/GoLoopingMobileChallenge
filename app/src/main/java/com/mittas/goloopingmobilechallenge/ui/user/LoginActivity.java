package com.mittas.goloopingmobilechallenge.ui.user;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mittas.goloopingmobilechallenge.R;
import com.mittas.goloopingmobilechallenge.util.Utility;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if(Utility.isLoggedIn(this)) {
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
