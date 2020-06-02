package com.crocodile.tinder;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Pref {

private SharedPreferences msharedPreferences;

public Pref(Context context) {
    msharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
}

public void saveLink(String link) {
    SharedPreferences.Editor editor = msharedPreferences.edit();
    editor.putString("link",link);
    editor.commit();
}

public String getLink() {
   return msharedPreferences.getString("link","");
}

}
