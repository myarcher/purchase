package com.commodity.purchase.until;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class AppShareUntil {
    private static AppShareUntil shareUntil;
    private SharedPreferences preferences;
    private Editor editor;

    private AppShareUntil(String name, Context context) {
        preferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public static AppShareUntil getInstance(String name, Context context) {
        if (shareUntil == null) {
            shareUntil = new AppShareUntil(name, context);
        }
        return shareUntil;
    }

    public int getInt(String key, int value) {
        return preferences.getInt(key, value);
    }

    public void putInt(String key, int value) {
        editor.putInt(key, value);
        editor.commit();
    }

    public AppShareUntil putS(String key, String value) {
        if (value == null || value.equals("null")) {
            value = "";
        }
        editor.putString(key, value);
        return this;
    }

    public AppShareUntil putB(String key, Boolean value) {
        editor.putBoolean(key, value);
        return this;
    }

    public AppShareUntil putF(String key, Float value) {
        editor.putFloat(key, value);
        return this;
    }

    public void commit() {
        editor.commit();
    }

    public AppShareUntil putI(String key, int value) {
        editor.putInt(key, value);
        return this;
    }

    public String getString(String key, String value) {
        return preferences.getString(key, value);
    }

    public void putString(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

    public boolean getBoolean(String key, boolean bo) {
        return preferences.getBoolean(key, bo);
    }

    public void putBoolean(String key, Boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
    }

    public float getFloat(String key, float lo) {
        return preferences.getFloat(key, lo);
    }

    public void putFloat(String key, Float value) {
        editor.putFloat(key, value);
        editor.commit();
    }

    public long getLong(String key, long lo) {
        return preferences.getLong(key, lo);
    }

    public void putLong(String key, long value) {
        editor.putLong(key, value);
        editor.commit();
    }
}
