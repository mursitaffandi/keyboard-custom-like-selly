package com.inspiraspace.jokulid.presenter.shippmentfare;

import com.inspiraspace.jokulid.model.rajaongkir.Result;
import com.inspiraspace.jokulid.network.ongkir.PulseOngkir;
import com.inspiraspace.jokulid.presenter.GeneratorOngkir;
import com.inspiraspace.jokulid.utils.Constant;

import java.util.List;

/**
 * Created by mursitaffandi on 4/18/18.
 */

public class PShippmentFare implements OnPresentShippmentfare, PulseOngkir{
    String weightgram;
    String originID;
    String destinationID;
    GeneratorOngkir generatorOngkir;

    @Override
    public void OnCount(String weight, String originID, String destinantionId) {
        this.weightgram = weight;
        this.originID = originID;
        this.destinationID = destinantionId;

        countFare();
    }

    private void countFare() {
        generatorOngkir = new GeneratorOngkir(this);
        generatorOngkir.getOngkir(
                originID,
                "subdistrict",
                destinationID,
                "subdistrict",
                weightgram,
                Constant.COMPANY_ENABLE_SERVICE
        );
    }

    @Override
    public void onSuccessGetOngkir(List<Result> resultOngkir) {

    }

    @Override
    public void onFailOccureTransactions(String errmsg) {

    }
}
