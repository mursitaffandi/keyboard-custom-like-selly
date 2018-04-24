package com.inspiraspace.jokulid.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.inspiraspace.jokulid.JokulidApplication;
import com.inspiraspace.jokulid.model.SampleSubdistrictID;

/**
 * Created by mursitaffandi on 4/3/18.
 */

public class Constant {

    public static final String URI_KEYBOARD_JOKULID = "com.inspiraspace.jokulid/.keylogger.softkeyboard.SoftKeyboard";
    public static final String TAG_ID_DETAIL_TRANSACTION = "id_transaction";
    public static final String TAG_TRANSACTION = "transaction";
    public static final String USER_ID = "1";
    public static final String HEADER_AUTHONRIZATION_KEY = "Authorization";
    public static final String HEADER_AUTHONRIZATION_VALUE = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiIxNzQuMTM4LjMxLjE5NCIsImV4cCI6MTUwMjY4MjIzODYyNywic3ViIjp7ImlkIjoyLCJ1c2VybmFtZSI6ImFndXNtIn0sImlhdCI6MTUwMjY3ODYzOCwianRpIjoidXNlciJ9.Fm-1k-o2PN24x5BF8mFTb1Q_Xb55XDem6_oBcynA_yQ";
    public static final String JSON_SUBDISTRCT = "{\"response\":[{\"id\":\"0\",\"subdistrict\":\"argomulyo\",\"city\":\"salatiga\",\"state\":\"jawa tengah\"},{\"id\":\"1\",\"subdistrict\":\"gamping\",\"city\":\"sleman\",\"state\":\"DIY\"},{\"id\":\"2\",\"subdistrict\":\"gunung pati\",\"city\":\"semarang\",\"state\":\"jawa tengah\"}]}";
    public static final String COMPANY_ENABLE_SERVICE = JokulidApplication.getInstance().getShippmentCompany();
    public static final int CODE_TRANSACTION_PENDING = 0;
    public static final String KEY_INTENT_TEMPALTE = "intent_template";
    public static final String KEY_TEMPLATE_AFTERPAID = "template_afterpaid";
    public static final String KEY_TEMPLATE_REMINDERTOPAY = "template_remindertopay";
    public static final String KEY_TEMPLATE_SENDRESI = "template_sendresi";

    public static SampleSubdistrictID getSampleSubdistrictID() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        SampleSubdistrictID itemObject = gson.fromJson(Constant.JSON_SUBDISTRCT, SampleSubdistrictID.class);
        return itemObject;
    }
    public static final String URL_WA_DIRECTCHAT = "https://api.whatsapp.com/send?phone=";
}
