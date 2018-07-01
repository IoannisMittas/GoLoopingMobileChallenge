package com.mittas.goloopingmobilechallenge.ui.user;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mittas.goloopingmobilechallenge.R;


public class ProfileFragment extends Fragment {  public static final String TAG = "LOGIN_FRAG";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

}
