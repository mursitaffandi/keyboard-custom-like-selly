package com.inspiraspace.jokulid.utils;

import com.inspiraspace.jokulid.R;

/**
 * Created by mursitaffandi on 4/3/18.
 */

public class Constant {
    public static final String TAG_ID_DETAIL_TRANSACTION = "id_transaction";
    public static final String TAG_TRANSACTION = "transaction";
    public static final String USER_ID = "1";
    public static final String HEADER_AUTHONRIZATION_KEY = "Authorization";
    public static final String HEADER_AUTHONRIZATION_VALUE = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiIxNzQuMTM4LjMxLjE5NCIsImV4cCI6MTUwMjY4MjIzODYyNywic3ViIjp7ImlkIjoyLCJ1c2VybmFtZSI6ImFndXNtIn0sImlhdCI6MTUwMjY3ODYzOCwianRpIjoidXNlciJ9.Fm-1k-o2PN24x5BF8mFTb1Q_Xb55XDem6_oBcynA_yQ";
    public static final String JSON_CHATAPPS = "{\"chatapps\": [{\"chattapp_id\" : \"0\",\"chattapp_appname\" : \"WA\",\"chattapp_appfullname\" : \"WhatsApp\",\"chattapp_apppackage\" : \"com.whatsapp\"},{\"chattapp_id\" : \"1\",\"chattapp_appname\" : \"Messenger\",\"chattapp_appfullname\" : \"Facebook Messenger\",\"chattapp_apppackage\" : \"com.facebook.orca\"},{\"chattapp_id\" : \"2\",\"chattapp_appname\" : \"IG\",\"chattapp_appfullname\" : \"Instagram\",\"chattapp_apppackage\" : \"com.instagram.android\"},{\"chattapp_id\" : \"3\",\"chattapp_appname\" : \"Line@\",\"chattapp_appfullname\" : \"Line@\",\"chattapp_apppackage\" : \"com.linecorp.lineat.android\"}]}";
    public static final int[] LIST_IC_CHATAPP = {
            R.drawable.ic_chatapp_wa,
            R.drawable.ic_chatapp_fb_msg,
            R.drawable.ic_chatapp_ig,
            R.drawable.ic_payment_mandiri
    };

    public static final String URL_WA_DIRECTCHAT = "https://api.whatsapp.com/send?phone=";
}
