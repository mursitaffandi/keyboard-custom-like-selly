package com.inspiraspace.jokulid.utils;

import com.inspiraspace.jokulid.JokulidApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by mursitaffandi on 4/3/18.
 */

public class Constant {
    public static HashMap<String, String> userInfo = JokulidApplication.getInstance().getUserInfo();
    public static final String SESSION_USER_ID = userInfo.get(JokulidApplication.KEY_USER_ID);
    public static final String SESSION_TOKO_ID = userInfo.get(JokulidApplication.KEY_TOKO_ID);
    public static final String SESSION_USER_NAME = userInfo.get(JokulidApplication.KEY_USER_NAME);
    public static final String SESSION_USER_EMAIL = userInfo.get(JokulidApplication.KEY_USER_EMAIL);
    public static final String SESSION_USER_TOKEN = userInfo.get(JokulidApplication.KEY_USER_TOKEN);
    public static final String SESSION_USER_UID = userInfo.get(JokulidApplication.KEY_USER_UID);
    public static final String SESSION_TOKO_NAME = userInfo.get(JokulidApplication.KEY_TOKO_NAME);
    public static final String SESSION_TOKO_NOHP = userInfo.get(JokulidApplication.KEY_TOKO_NOHP);
    public static final String SESSION_TOKO_URL = userInfo.get(JokulidApplication.KEY_TOKO_URL);

    public static final String URI_KEYBOARD_JOKULID = "com.inspiraspace.jokulid/.keylogger.softkeyboard.SoftKeyboard";
    public static final String TAG_ID_DETAIL_TRANSACTION = "id_transaction";
    public static final String TAG_TRANSACTION = "transaction";
    public static final String HEADER_AUTHONRIZATION_KEY = "Authorization";
    public static final String HEADER_AUTHONRIZATION_VALUE = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiIxNzQuMTM4LjMxLjE5NCIsImV4cCI6MTUwMjY4MjIzODYyNywic3ViIjp7ImlkIjoyLCJ1c2VybmFtZSI6ImFndXNtIn0sImlhdCI6MTUwMjY3ODYzOCwianRpIjoidXNlciJ9.Fm-1k-o2PN24x5BF8mFTb1Q_Xb55XDem6_oBcynA_yQ";
    public static final List<String> COMPANY_ENABLE_SERVICE = JokulidApplication.getInstance().getShippmentCompany();
    public static final int CODE_TRANSACTION_PENDING = 0;
    public static final String KEY_INTENT_TEMPALTE = "intent_template";
    public static final String KEY_TEMPLATE_AFTERPAID = "template_afterpaid";
    public static final String KEY_TEMPLATE_REMINDERTOPAY = "template_remindertopay";
    public static final String KEY_TEMPLATE_SENDRESI = "template_sendresi";
    public static final String URL_WA_DIRECTCHAT = "https://api.whatsapp.com/send?phone=";
    public static final String KEY_SEARCH_TRANSACTION = "keyword_transaction";
    public static final String DEFAULT_TEMPLATE = "Hello world \n" +
            "#account-name\n" +
            "#account-number\n" +
            "#amount\n" +
            "#bank-name\n" +
            "#bank-address\n" +
            "#customer-address\n" +
            "#customer-contact\n" +
            "#customer-name\n" +
            "#invoice-id\n" +
            "#notes\n" +
            "#product-list\n" +
            "#shipping-cost\n" +
            "#store-name\n" +
            "#total-amount\n" +
            "#total-amount\n" +
            "#shipping-provider\n" +
            "#resi-number\n" +
            "\n" +
            "World End";
    private static String[] STR_SHIPPMENTCOMPANIES = {"jne", "sicepat", "pos", "tiki", "jnt", "wahana"};
    public static final List<String> LIST_SHIPPMENTCOMPANY = new ArrayList<String>(Arrays.asList(STR_SHIPPMENTCOMPANIES));
    public static final String[] LIST_TAG_KEY_TEMPLATE = {
            "#account-name",
            "#account-number",
            "#amount",
            "#bank-name",
            "#bank-address",
            "#customer-address",
            "#customer-contact",
            "#customer-name",
            "#invoice-id",
            "#notes",
            "#product-list",
            "#shipping-cost",
            "#store-name",
            "#total-amount",
            "#total-amount",
            "#shipping-provider",
            "#resi-number"
    };
}
