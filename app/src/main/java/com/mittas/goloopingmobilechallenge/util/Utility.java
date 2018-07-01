package com.mittas.goloopingmobilechallenge.util;

import android.widget.EditText;

public class Utility {
    public static boolean isEditTextEmpty(EditText editText) {
        if (editText.getText().toString().trim().length() > 0) {
            return false;
        }
        return true;
    }
}
