package com.mittas.goloopingmobilechallenge.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.mittas.goloopingmobilechallenge.R;

import static android.content.Context.MODE_PRIVATE;

public class Utility {
    public static final String PREFS_NAME = "PreferencesFile";

    public static boolean isLoggedIn(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean defaultValue = Boolean.valueOf(context.getResources().getString(R.string.is_logged_in_default));
        return prefs.getBoolean(context.getString(R.string.is_logged_in_key), defaultValue);
    }

}
