package com.abc.code.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


public class SpUtil {

    static SharedPreferences prefs;

    public static void init(Context context) {
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static String getData(String key) {
        return prefs.getString(key, "");
    }

    public static long getLong(String key) {
        return prefs.getLong(key,0);
    }

    public static void setData(String key, String data) {
        prefs.edit().putString(key, data).apply();
    }

    public static void setLong(String key, long data) {
        prefs.edit().putLong(key, data).apply();
    }

    public static boolean getBoolean(String key) {
        return prefs.getBoolean(key, false);
    }

    public static void setBoolean(String key, boolean data) {
        prefs.edit().putBoolean(key, data).apply();
    }

}
