package com.inspiraspace.jokulid.network.main;

/**
 * Created by mursitaffandi on 4/30/18.
 */

public interface PulsePostTransaction {
    void OnSuccessAddTransaction();
    void OnErrorAddTransaction(String errmsg);
}
