package com.inspiraspace.jokulid.presenter;

import com.inspiraspace.jokulid.network.main.ClientMainCall;
import com.inspiraspace.jokulid.network.main.PulseTransactionStatus;
import com.inspiraspace.jokulid.utils.Constant;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mursitaffandi on 4/27/18.
 */

public class PostTransactionStatus {
    private ClientMainCall clientMainCall = new ClientMainCall();
    private Call<Void> apiCall;
    private PulseTransactionStatus pulseTransactionStatus;

    public PostTransactionStatus(PulseTransactionStatus pulseTransactionStatus) {
        this.pulseTransactionStatus = pulseTransactionStatus;
    }

    public void updateTransactionStatus(String transaction_id, String new_status){
        apiCall = clientMainCall.getService().updateTransactionStatus(Constant.SESSION_USER_ID, transaction_id,new_status);
        apiCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                pulseTransactionStatus.OnSuccessUpdateTransactionStatus();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                pulseTransactionStatus.OnErrorUpdateTransactionStatus(t.getMessage());
            }
        });
    }
}
