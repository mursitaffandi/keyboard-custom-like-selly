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
    public static final String KEY_USER_ISAUTHED = "user_isauthed";

    /*
    * credentials KEY_TOKO
    * */
    public static final String KEY_TOKO_ID = "toko_id";
    public static final String KEY_TOKO_NAME = "toko_name";
    public static final String KEY_TOKO_NOHP = "toko_nohp";
    public static final String KEY_TOKO_URL = "toko_url";


    /*
    * credentials KEY_SHIPPMENT
    * */
    public static final String KEY_SET_SHIPPMENT_COMPANY = "key_shippmentcompany";
    public static final String KEY_SHIPPMENT_WEIGHT = "shippment_weight";
    public static final String KEY_SHIPPMENT_ORIGIN = "shippment_origin";
    public static final String KEY_SHIPPMENT_ORIGIN_ID = "shippment_origin_id";

    /*
    * Template KEY_TEMPLATE
    * */
    /*public static final String KEY_TEMPLATE_SENDRESI = "template_sendresi";
    public static final String KEY_TEMPLATE_AFTERPAID = "template_afterpaid";
    public static final String KEY_TEMPLATE_REMAINDTOPAY = "template_remaindtopay";*/

    // @session = for getting info from Preferences
    SharedPreferences session;
    // @editor = for setting info to Preferences
    SharedPreferences.Editor editor;

    public void setLoginToken(String token) {
        editor.putString(KEY_USER_TOKEN, token);
        editor.commit();
    }

    public String getLoginToken() {
        return session.getString(KEY_USER_TOKEN, null);
    }

    public void setLoginInfo(
            String user_name,
            String user_id,
            String user_email,
            String user_token,
            String user_uid,
            String toko_id,
            String shippment_origin,
            String shippment_origin_id,
            String shippmentWeight,
            String toko_name, String toko_nohp, String toko_url) {
        editor.putString(KEY_USER_NAME, user_name);
        editor.putString(KEY_USER_ID, user_id);
        editor.putString(KEY_USER_EMAIL, user_email);
        editor.putString(KEY_USER_TOKEN, user_token);
        editor.putString(KEY_USER_UID, user_uid);
        editor.putString(KEY_TOKO_ID, toko_id);
        editor.putString(KEY_TOKO_NAME, toko_name);
        editor.putString(KEY_TOKO_NOHP, toko_nohp);
        editor.putString(KEY_TOKO_URL, toko_url);

        editor.putBoolean(KEY_USER_ISAUTHED, true);
        editor.commit();

        setShippmentOrigin(shippment_origin, shippment_origin_id);
        setShippmentWeight(shippmentWeight);

    }

    public boolean getStatusUserIsauthed() {
        return session.getBoolean(KEY_USER_ISAUTHED,false);
    }

    public void setLoginInfo(
            String user_name,
            String user_id,
            String user_email,
            String user_token,
            String user_uid,
            String toko_id,
            String toko_name, String toko_nohp, String toko_url) {
        editor.putString(KEY_USER_NAME, user_name);
        editor.putString(KEY_USER_ID, user_id);
        editor.putString(KEY_USER_EMAIL, user_email);
        editor.putString(KEY_USER_TOKEN, user_token);
        editor.putString(KEY_USER_UID, user_uid);
        editor.putString(KEY_TOKO_ID, toko_id);
        editor.putString(KEY_TOKO_NAME, toko_name);
        editor.putString(KEY_TOKO_NOHP, toko_nohp);
        editor.putString(KEY_TOKO_URL, toko_url);

        editor.putBoolean(KEY_USER_ISAUTHED, true);
        editor.commit();
    }


    public HashMap<String, String> getUserInfo() {
        HashMap<String, String> map = new HashMap<>();
        map.put(KEY_USER_NAME, session.getString(KEY_USER_NAME, null));
        map.put(KEY_USER_ID, session.getString(KEY_USER_ID, null));
        map.put(KEY_USER_EMAIL, session.getString(KEY_USER_EMAIL, null));
        map.put(KEY_USER_TOKEN, session.getString(KEY_USER_TOKEN, null));
        map.put(KEY_USER_UID, session.getString(KEY_USER_UID, null));
        map.put(KEY_TOKO_ID, session.getString(KEY_TOKO_ID, null));
        map.put(KEY_TOKO_NAME, session.getString(KEY_TOKO_NAME, null));
        map.put(KEY_TOKO_NOHP, session.getString(KEY_TOKO_NOHP, null));
        map.put(KEY_TOKO_URL, session.getString(KEY_TOKO_URL, null));

        return map;
    }

    public String getTokoId() {
        return session.getString(KEY_TOKO_ID, null);
    }


    public void clearLoginInfo() {
        editor.putBoolean(KEY_USER_ISAUTHED, false);
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

    public void setTemplate(String template, String template_sendresi){
        editor.putString(template, template_sendresi);
        editor.commit();
    }

    public String getTemplate(String template){
        return session.getString(template,Constant.DEFAULT_TEMPLATE);
    }

}
