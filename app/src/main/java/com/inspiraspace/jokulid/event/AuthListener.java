package com.inspiraspace.jokulid.event;

/**
 * Created by mursitaffandi on 5/3/18.
 */

public interface AuthListener {
    void OnSuccesLogin();
    void OnFailLogin();
    void OnUnauthedSession();
    void OnLogout();
}
