package com.inspiraspace.jokulid.presenter;

import com.inspiraspace.jokulid.model.autotext.Mautotext;
import com.inspiraspace.jokulid.model.bank.Bank;
import com.inspiraspace.jokulid.network.main.ClientMainCall;
import com.inspiraspace.jokulid.network.main.PulseAutoText;
import com.inspiraspace.jokulid.network.main.PulseBank;
import com.inspiraspace.jokulid.utils.Constant;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by mursitaffandi on 4/24/18.
 */

public class GeneratorBank {
    private ClientMainCall clientMainCall = new ClientMainCall();
    private Call<Bank> apiCall;

    public GeneratorBank(final PulseBank pulseMainServer) {
        apiCall = clientMainCall.getService().getBank();
        apiCall.enqueue(new Callback<Bank>() {
            @Override
            public void onResponse(Call<Bank> call, retrofit2.Response<Bank> response) {
                pulseMainServer.OnSuccesGetBanks(response.body().getResponse());
            }

            @Override
            public void onFailure(Call<Bank> call, Throwable t) {
                pulseMainServer.OnErrorGetBank(t.getMessage());
            }
        });
    }
}
