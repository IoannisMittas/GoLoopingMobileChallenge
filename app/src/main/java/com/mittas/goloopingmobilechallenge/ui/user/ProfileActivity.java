package com.mittas.goloopingmobilechallenge.ui.user;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mittas.goloopingmobilechallenge.R;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        if (savedInstanceState == null) {
            ProfileFragment fragment = new ProfileFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, fragment, ProfileFragment.TAG).commit();
        }
    }

}
