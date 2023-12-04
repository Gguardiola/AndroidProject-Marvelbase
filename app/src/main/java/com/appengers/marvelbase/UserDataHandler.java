package com.appengers.marvelbase;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.UUID;

public class UserDataHandler {

    //// FIRST RUN HANDLER METHODS ////
    public static boolean isFirstRun(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("USER_DATA", Context.MODE_PRIVATE);
        return prefs.getBoolean("FIRST_RUN_CHECK", true);
    }

    public static String setFirstRun(Context context, boolean value) {
        SharedPreferences prefs = context.getSharedPreferences("USER_DATA", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("FIRST_RUN_CHECK", value);
        editor.apply();
        return setUserId(context);
    }

    public static String setUserId(Context context){
        String randomComponent = UUID.randomUUID().toString();
        SharedPreferences prefs = context.getSharedPreferences("USER_DATA", Context.MODE_PRIVATE);
        String randomId = System.currentTimeMillis() + "-" + randomComponent;
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("USER_ID", randomId);
        editor.apply();
        Log.d("USER ID SET:",String.valueOf(randomId));
        return randomId;
    }
}
