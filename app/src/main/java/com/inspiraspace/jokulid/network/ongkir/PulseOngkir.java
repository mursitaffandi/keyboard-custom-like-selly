package com.inspiraspace.jokulid.network.ongkir;

import com.inspiraspace.jokulid.model.Response;
import com.inspiraspace.jokulid.model.rajaongkir.Result;

import java.util.List;

/**
 * Created by mursitaffandi on 4/5/18.
 */

public interface PulseOngkir {
    void onSuccessGetOngkir(List<Result> resultOngkir);
    void onFailOccureTransactions(String errmsg);
}
