package com.mittas.goloopingmobilechallenge.ui.user;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mittas.goloopingmobilechallenge.R;
import com.mittas.goloopingmobilechallenge.viewmodel.LoginViewModel;

public class LoginFragment extends Fragment {
    public static final String TAG = "LOGIN_FRAG";

    private LoginViewModel viewModel;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);

        usernameEditText = rootView.findViewById(R.id.text_input_username);
        passwordEditText = rootView.findViewById(R.id.text_input_password);

        loginButton = rootView.findViewById(R.id.button_login);
        loginButton.setOnClickListener(view -> onLoginButtonPressed());

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        viewModel = ViewModelProviders.of(getActivity()).get(LoginViewModel.class);
    }

    private void onLoginButtonPressed() {
        if(isEditTextEmpty(usernameEditText)) {
            Toast.makeText(getActivity(), "Please enter a username", Toast.LENGTH_SHORT);
            return;
        }

        if(isEditTextEmpty(passwordEditText)) {
            Toast.makeText(getActivity(), "Please enter a password", Toast.LENGTH_SHORT);
            return;
        }

        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if (viewModel != null) {
            viewModel.onLoginRequest(username, password);
        }
    }

    private boolean isEditTextEmpty(EditText editText) {
        if (editText.getText().toString().trim().length() > 0) {
            return false;
        }
        return true;
    }

}
