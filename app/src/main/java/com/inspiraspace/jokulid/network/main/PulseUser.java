package com.inspiraspace.jokulid.network.main;

import com.inspiraspace.jokulid.model.user.Response;

/**
 * Created by mursitaffandi on 4/26/18.
 */

public interface PulseUser {
    void OnSuccessUser(Response responseUsers);
    void OnErrorUser(String errmsg);

    void OnSuccessUpdateUser();
    void OnErrorUpdateUser(String errmsg);
}
