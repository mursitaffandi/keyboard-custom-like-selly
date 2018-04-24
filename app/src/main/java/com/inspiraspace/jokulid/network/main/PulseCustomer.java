package com.inspiraspace.jokulid.network.main;

import com.inspiraspace.jokulid.model.customers.ResponseItem;

import java.util.List;

/**
 * Created by mursitaffandi on 4/19/18.
 */

public interface PulseCustomer {
    void OnSuccesGetCustomers(List<ResponseItem> responseItems);
    void OnErrorGetCustomers(String errmsg);
}
