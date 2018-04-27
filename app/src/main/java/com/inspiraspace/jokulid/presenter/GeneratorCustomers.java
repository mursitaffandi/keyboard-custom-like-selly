package com.inspiraspace.jokulid.presenter;

import com.inspiraspace.jokulid.model.autotext.Mautotext;
import com.inspiraspace.jokulid.model.customers.Customers;
import com.inspiraspace.jokulid.network.main.ClientMainCall;
import com.inspiraspace.jokulid.network.main.PulseAutoText;
import com.inspiraspace.jokulid.network.main.PulseCustomer;
import com.inspiraspace.jokulid.utils.Constant;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by mursitaffandi on 4/19/18.
 */

public class GeneratorCustomers {
    private PulseCustomer pulseMainServer;
    private ClientMainCall clientMainCall = new ClientMainCall();
    private Call<Customers> apiCall;

    public GeneratorCustomers(PulseCustomer pulseMainServer) {
        this.pulseMainServer = pulseMainServer;
    }

    public void getCustomers() {
        apiCall = clientMainCall.getService().getCustomers(Constant.USER_ID);
        apiCall.enqueue(new Callback<Customers>() {
            @Override
            public void onResponse(Call<Customers> call, retrofit2.Response<Customers> response) {
                pulseMainServer.OnSuccesGetCustomers(response.body().getResponse());
            }

            @Override
            public void onFailure(Call<Customers> call, Throwable t) {
                pulseMainServer.OnErrorGetCustomers(t.getMessage());
            }
        });
    }
}
