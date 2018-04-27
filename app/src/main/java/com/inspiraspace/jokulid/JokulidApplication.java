package com.inspiraspace.jokulid;

import android.app.Application;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.SharedPreferences;

import com.facebook.stetho.Stetho;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.inspiraspace.jokulid.utils.Constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by mursitaffandi on 4/5/18.
 */

public class JokulidApplication extends Application {
    private static JokulidApplication instance;
    private Gson gson;
    private SharedPreferences prefs;
    private ClipboardManager clipboard;
    private ClipData clip;

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        createGson();
        createPreference();
    }

    public JokulidApplication() {
        instance = this;
    }

    public static JokulidApplication getInstance() {
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

    public SharedPreferences getPrefs() {
        return prefs;
    }

    public static final String KEY_SHAREDPREF_NAME = "jokul";

    /*
    * credentials KEY_USER
    * */

    public static final String KEY_USER_NAME = "user_name";
    public static final String KEY_USER_ID = "user_id";
    public static final String KEY_USER_EMAIL = "user_email";
    public static final String KEY_USER_TOKEN = "user_token";
    public static final String KEY_USER_UID = "user_uid";

    /*
    * credentials KEY_TOKO
    * */

    public static final String KEY_TOKO_NAME = "toko_name";
    public static final String KEY_TOKO_NOHP = "toko_hohp";
    public static final String KEY_TOKO_URL = "toko_url";


    /*
    * credentials KEY_SHIPPMENT
    * */
    public static final String KEY_SET_SHIPPMENT_COMPANY = "key_shippmentcompany";
    public static final String KEY_SHIPPMENT_WEIGHT = "shippment_weight";
    public static final String KEY_SHIPPMENT_ORIGIN = "shippment_origin";
    public static final String KEY_SHIPPMENT_ORIGIN_ID = "shippment_origin_id";

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

    public void setLoginInfo(
            String user_name,
            String user_id,
            String user_email,
            String user_token,
            String user_uid,
            String toko_name,
            String toko_nohp,
            String toko_url
    ) {
        editor.putString(KEY_USER_NAME, user_name);
        editor.putString(KEY_USER_ID, user_id);
        editor.putString(KEY_USER_EMAIL, user_email);
        editor.putString(KEY_USER_TOKEN, user_token);
        editor.putString(KEY_USER_UID, user_uid);
        editor.putString(KEY_TOKO_NAME, toko_name);
        editor.putString(KEY_TOKO_NOHP, toko_nohp);
        editor.putString(KEY_TOKO_URL, toko_url);
        editor.commit();
    }
    public HashMap<String, String> getUserInfo() {
        HashMap<String, String> map = new HashMap<>();
        map.put(KEY_USER_NAME, session.getString(KEY_USER_NAME, "user0"));
        map.put(KEY_USER_ID, session.getString(KEY_USER_ID, "1"));
        map.put(KEY_USER_EMAIL, session.getString(KEY_USER_EMAIL, "user0@user.com"));
        map.put(KEY_USER_TOKEN, session.getString(KEY_USER_TOKEN, ""));
        map.put(KEY_USER_UID, session.getString(KEY_USER_UID, ""));
        return map;
    }

    public HashMap<String, String> getTokoInfo() {
        HashMap<String, String> map = new HashMap<>();
        map.put(KEY_TOKO_NAME, session.getString(KEY_TOKO_NAME, ""));
        map.put(KEY_TOKO_NOHP, session.getString(KEY_TOKO_NOHP, ""));
        map.put(KEY_TOKO_URL, session.getString(KEY_TOKO_URL, ""));
        return map;
    }


    public void clearLoginInfo() {
        editor.clear();
        editor.commit();
    }


    /*
    * https://stackoverflow.com/questions/7057845/save-arraylist-to-sharedpreferences
    * */
    public void setShippmentCompany(List<String> companies) {
        Set<String> set = new HashSet<String>();
        set.addAll(companies);
        editor.putStringSet(KEY_SET_SHIPPMENT_COMPANY, set);
        editor.commit();
    }

    public List<String> getShippmentCompany() {
        Set<String> tmp = new HashSet<String>();
        tmp.add("jne");
        Set<String> set = session.getStringSet(KEY_SET_SHIPPMENT_COMPANY, tmp);
        List<String> resultlist = new ArrayList<String>(set);
        return resultlist;
    }

    public void setShippmentOrigin(String shippment_origin, String shippment_origin_id) {
        editor.putString(KEY_SHIPPMENT_ORIGIN, shippment_origin);
        editor.putString(KEY_SHIPPMENT_ORIGIN_ID, shippment_origin_id);
        editor.commit();
    }

    public HashMap<String, String> getShippmentOrigin() {
        HashMap<String, String> map = new HashMap<>();
        map.put(KEY_SHIPPMENT_ORIGIN, session.getString(KEY_SHIPPMENT_ORIGIN, "Gamping, Sleman, DI Yogyakarta"));
        map.put(KEY_SHIPPMENT_ORIGIN_ID, session.getString(KEY_SHIPPMENT_ORIGIN_ID, "5782"));
        return map;
    }

    public void setShippmentWeight(String shippmentWeight) {
        editor.putString(KEY_SHIPPMENT_WEIGHT, shippmentWeight);
        editor.commit();
    }

    public String getShippmentWeight() {
        return session.getString(KEY_SHIPPMENT_WEIGHT, "1000");
    }

}
