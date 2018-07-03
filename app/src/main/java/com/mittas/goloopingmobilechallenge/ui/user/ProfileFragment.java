package com.mittas.goloopingmobilechallenge.ui.user;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mittas.goloopingmobilechallenge.R;
import com.mittas.goloopingmobilechallenge.data.User;
import com.mittas.goloopingmobilechallenge.viewmodel.ProfileViewModel;
import com.mittas.goloopingmobilechallenge.vo.Resource;
import com.mittas.goloopingmobilechallenge.vo.Status;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;


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
            PickImageDialog.build(new PickSetup())
                    .setOnPickResult(result -> {
                        Uri uri = result.getUri();
                        onNewAvatar(uri);
                    })
                    .show(getActivity());
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
                Glide.with(getActivity()).load(avatarUrl).into(profileImageView);
            }
        });
    }

    private void onNewAvatar(Uri imageUri) {
        if(viewModel != null) {
            viewModel.onNewAvatar(imageUri);
        }
    }

}
