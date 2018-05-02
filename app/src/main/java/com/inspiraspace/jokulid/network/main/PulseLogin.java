package com.inspiraspace.jokulid.network.main;

import com.inspiraspace.jokulid.model.login.Response;

/**
 * Created by mursitaffandi on 4/30/18.
 */

public interface PulseLogin {
    void OnSuccessLogin(Response responseLogin);
    void OnErrorLogin(String errmsg);
}
