package com.example.wastemanagementapp.utils;

import android.content.SharedPreferences;
import android.content.Context;
import android.content.SharedPreferences;
public class SessionManager {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    public SessionManager(Context context) {
        sharedPreferences = context.getSharedPreferences("USER_SESSION", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }
    public void createSession(String email) {
        editor.putString("USER_EMAIL", email);
        editor.apply();
    }
    public String getUserEmail() {
        return sharedPreferences.getString("USER_EMAIL", "");
    }
    public void logout() {
        editor.clear();
        editor.apply();
    }
}