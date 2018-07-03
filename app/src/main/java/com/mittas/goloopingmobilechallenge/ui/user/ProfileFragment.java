package com.mittas.goloopingmobilechallenge.ui.user;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.InvalidationTracker;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mittas.goloopingmobilechallenge.R;
import com.mittas.goloopingmobilechallenge.data.User;
import com.mittas.goloopingmobilechallenge.viewmodel.LoginViewModel;
import com.mittas.goloopingmobilechallenge.viewmodel.ProfileViewModel;
import com.mittas.goloopingmobilechallenge.vo.Resource;
import com.mittas.goloopingmobilechallenge.vo.Status;


public class ProfileFragment extends Fragment {
    public static final String TAG = "PROFILE_FRAG";

    private ProfileViewModel viewModel;
    private ImageView profileImageView;
    private TextView emailTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        emailTextView = rootView.findViewById(R.id.email_textview);

        profileImageView = rootView.findViewById(R.id.profile_imageview);
        profileImageView.setOnClickListener(view -> {

        });

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        viewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);
        subscribeUi();
    }

    private void subscribeUi() {
        viewModel.getUser().observe(this, (Observer<Resource<User>>) userResource -> {
            if (userResource.status == Status.SUCCESS) {
                User user = userResource.data;

                String email = user.getEmail();
                emailTextView.setText(email);

                String avatarUrl = user.getAvatarUrl();
//                RequestOptions requestOptions = new RequestOptions()
//                        .override(200, 200);
//                Glide.with(getActivity()).load(avatarUrl)
//                        .apply(requestOptions)
//                        .thumbnail(0.5f).into(profileImageView);

                Glide.with(getActivity()).load(avatarUrl).into(profileImageView);
            }
        });
    }

}
