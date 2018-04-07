package com.inspiraspace.jokulid.network.main;

import com.inspiraspace.jokulid.model.transactions.Response;

import java.util.List;

/**
 * Created by mursitaffandi on 4/2/18.
 */

public interface PulseMainServer {
    void onSuccessGetTransactions(List<Response> transaction);
    void onFailOccureTransactions(String errmsg);
}
