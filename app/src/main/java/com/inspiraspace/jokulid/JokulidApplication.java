package com.inspiraspace.jokulid;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.facebook.stetho.Stetho;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;

/**
 * Created by mursitaffandi on 4/5/18.
 */

public class JokulidApplication extends Application {
    private static JokulidApplication instance;
    private Gson gson;
    private SharedPreferences prefs;

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        createGson();
        createPreference();
        setCompanyShippment("jne");
    }

    public JokulidApplication(){
        instance = this;
    }

    public static JokulidApplication getInstance(){
        return instance;
    }

    private void createPreference() {
//        prefs = PreferenceManager.getDefaultSharedPreferences(instance);
        session = instance.getSharedPreferences(KEY_SHAREDPREF_NAME, instance.MODE_PRIVATE);
        editor = session.edit();
    }

    private void createGson() {
        gson = new GsonBuilder().create();
    }

    public Gson getGson() {
        return gson;
    }

    public SharedPreferences getPrefs(){
        return prefs;
    }
    public static final String KEY_SHAREDPREF_NAME = "jokul";

    /*
    * credentials KEY_USER
    * */
    public static final String KEY_USER_TOKEN = "user_token";
    public static final String KEY_USER_NAME = "user_name";
    public static final String KEY_USER_UID = "user_uid";
    public static final String KEY_USER_EMAIL = "user_email";
    public static final String KEY_USER_ID = "user_id";

    /*
    * Shippment KEY
    * */
    public static final String KEY_COMPANYSHIPPMENT = "cs_shippment";

    // @session = for getting info from Preferences
    SharedPreferences session;
    // @editor = for setting info to Preferences
    SharedPreferences.Editor editor;

    public void setLoginToken(String token) {
        editor.putString(KEY_USER_TOKEN, token);
        editor.commit();
    }

    public String getLoginToken() {
        return session.getString(KEY_USER_TOKEN, "");
    }

    public void setLoginInfo(String user_name, String user_uid, String user_email, String user_id) {
        editor.putString(KEY_USER_NAME, user_name);
        editor.putString(KEY_USER_UID, user_uid);
        editor.putString(KEY_USER_EMAIL, user_email);
        editor.putString(KEY_USER_ID, user_id);
        editor.commit();
    }

    public HashMap<String, String> getLoginInfo() {
        HashMap<String, String> map = new HashMap<>();
        map.put(KEY_USER_NAME, session.getString(KEY_USER_NAME, ""));
        map.put(KEY_USER_UID, session.getString(KEY_USER_UID, ""));
        map.put(KEY_USER_EMAIL, session.getString(KEY_USER_EMAIL, ""));
        map.put(KEY_USER_ID, session.getString(KEY_USER_ID, ""));
        return map;
    }

    public void clearLoginInfo() {
        editor.clear();
        editor.commit();
    }

    public void setCompanyShippment(String companies) {
        editor.putString(KEY_COMPANYSHIPPMENT, companies);
        editor.commit();
    }

    public String getCompanyShippment() {
        return session.getString(KEY_COMPANYSHIPPMENT, "jne");
    }

}
