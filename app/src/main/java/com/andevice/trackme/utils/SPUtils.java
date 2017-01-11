package com.andevice.trackme.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.HashMap;


public class SPUtils {
    private SharedPreferences.Editor editor;
    private SharedPreferences sharedpreferences;

    public static final String PREFERENCES = "track_me";
    public static final String REGISTERED_OTP = "registered_otp";
    public static final String USER_NAME = "user_name";

    public SPUtils(Context context) {
        if (context != null)
            sharedpreferences = context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
    }

    public String getString(String tag) {
        return sharedpreferences.getString(tag, "");
    }

    public Boolean getBoolean(String tag) {
        return sharedpreferences.getBoolean(tag, false);
    }

    public String getDate(String tag) {
        return sharedpreferences.getString(tag, "1 Jan 2015 00:00:00");
    }

    public void setValue(String tag, String value) {
        editor = sharedpreferences.edit();
        editor.putString(tag, value);
        editor.apply();
    }

    public void setValue(String tag, boolean value) {
        editor = sharedpreferences.edit();
        editor.putBoolean(tag, value);
        editor.apply();
    }


    public void clearPreferences() {
        editor = sharedpreferences.edit();
        editor.clear();
        editor.apply();
    }
}