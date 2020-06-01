package com.crocodile.tinder;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Pref {

private SharedPreferences msharedPreferences;

public Pref(Context context) {
    msharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
}

public void saveModer() {
    SharedPreferences.Editor editor = msharedPreferences.edit();
    editor.putBoolean("ok",true);
    editor.commit();
}

public Boolean getModer() {
   return msharedPreferences.getBoolean("ok",false);
}

}
