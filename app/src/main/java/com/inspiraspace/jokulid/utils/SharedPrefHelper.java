package com.inspiraspace.jokulid.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

/**
 * Created by mursitaffandi on 4/17/18.
 */

public class SharedPrefHelper {

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

    public SharedPrefHelper(Context context) {
        session = context.getSharedPreferences(KEY_SHAREDPREF_NAME, context.MODE_PRIVATE);
        editor = session.edit();
    }

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
