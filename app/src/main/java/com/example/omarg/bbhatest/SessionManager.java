package com.example.omarg.bbhatest;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private Context context;

    SessionManager(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences("MyApp", Context.MODE_PRIVATE);
        editor = preferences.edit();
        editor.apply();
    }

    void setLoggedIn(Boolean loggedIn) {
        editor.putBoolean("loggedInMode", loggedIn);
        editor.commit();
    }

    boolean loggedIn() {
        return preferences.getBoolean("loggedInMode",false);
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

}
