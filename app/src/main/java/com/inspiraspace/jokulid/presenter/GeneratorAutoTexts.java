package com.inspiraspace.jokulid.presenter;

import com.inspiraspace.jokulid.model.autotext.Mautotext;
import com.inspiraspace.jokulid.network.main.ClientMainCall;
import com.inspiraspace.jokulid.network.main.PulseAutoText;
import com.inspiraspace.jokulid.utils.Constant;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by mursitaffandi on 4/12/18.
 */

public class GeneratorAutoTexts {
    private PulseAutoText pulseMainServer;
    private ClientMainCall clientMainCall = new ClientMainCall();
    private Call<Mautotext> apiCall;

    public GeneratorAutoTexts(PulseAutoText pulseMainServer) {
        this.pulseMainServer = pulseMainServer;
    }

    public void getAutoText(String keyword) {
        if (keyword=="") keyword="a";
        apiCall = clientMainCall.getService().getAutoText(Constant.SESSION_USER_ID, keyword);
        apiCall.enqueue(new Callback<Mautotext>() {
            @Override
            public void onResponse(Call<Mautotext> call, retrofit2.Response<Mautotext> response) {
                pulseMainServer.onSuccess(response.body().getResponse());
            }

            @Override
            public void onFailure(Call<Mautotext> call, Throwable t) {
                pulseMainServer.onError(t.getMessage());
            }
        });
    }
}
