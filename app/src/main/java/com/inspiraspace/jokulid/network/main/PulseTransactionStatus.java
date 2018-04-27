package com.inspiraspace.jokulid.network.main;

/**
 * Created by mursitaffandi on 4/27/18.
 */

public interface PulseTransactionStatus {
    void OnSuccessUpdateTransactionStatus();
    void OnErrorUpdateTransactionStatus(String errmsg);
}
