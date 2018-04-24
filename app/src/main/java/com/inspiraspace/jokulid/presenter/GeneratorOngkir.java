package com.inspiraspace.jokulid.presenter;

import com.inspiraspace.jokulid.model.rajaongkir.FeeShipping;
import com.inspiraspace.jokulid.model.rajaongkir.Rajaongkir;
import com.inspiraspace.jokulid.model.rajaongkir.Result;
import com.inspiraspace.jokulid.network.ongkir.ClientRajaongkirproCall;
import com.inspiraspace.jokulid.network.ongkir.PulseOngkir;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mursitaffandi on 4/4/18.
 */

public class GeneratorOngkir {
    private PulseOngkir pulseMainServer;
    private ClientRajaongkirproCall clientMainCall = new ClientRajaongkirproCall();
    private Call<FeeShipping> apiCall;
    private List<Result> listResultOngkir;

    public GeneratorOngkir(PulseOngkir pulseMainServer) {
        this.pulseMainServer = pulseMainServer;
    }

    public void getOngkir(String origin,
                          String originType,
                          String destination,
                          String destinationType,
                          String weight,
                          String courier) {

        apiCall = clientMainCall.getService().requestfee(origin,
                originType,
                destination,
                destinationType,
                weight,
                courier);

        apiCall.enqueue(new Callback<FeeShipping>() {
            @Override
            public void onResponse(Call<FeeShipping> call, Response<FeeShipping> response) {
                listResultOngkir = response.body().getRajaongkir().getResults();
                pulseMainServer.onSuccessGetOngkir(listResultOngkir);
            }

            @Override
            public void onFailure(Call<FeeShipping> call, Throwable t) {
                pulseMainServer.onFailOccureTransactions(t.getMessage());
            }
        });
    }
}
