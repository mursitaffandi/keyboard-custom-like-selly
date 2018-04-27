package com.inspiraspace.jokulid.network.main;

import com.inspiraspace.jokulid.model.bank.ResponseItem;

import java.util.List;

/**
 * Created by mursitaffandi on 4/24/18.
 */

public interface PulseBank {
    void OnSuccesGetBanks(List<ResponseItem> banklist);
    void OnErrorGetBank(String errmsg);
}
