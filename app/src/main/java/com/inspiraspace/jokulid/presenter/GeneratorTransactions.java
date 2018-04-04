package com.inspiraspace.jokulid.presenter;

import com.inspiraspace.jokulid.utils.Constant;
import com.inspiraspace.jokulid.model.Transaction;
import com.inspiraspace.jokulid.network.ClientCall;
import com.inspiraspace.jokulid.network.PulseTransactions;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mursitaffandi on 4/2/18.
 */

public class GeneratorTransactions {
    PulseTransactions pulseTransactions;
    ClientCall clientCall = new ClientCall();
    private Call<Transaction> apiCall;
    private List<com.inspiraspace.jokulid.model.Response> listTransaction;
    int code_status_transaction;

    public GeneratorTransactions(PulseTransactions pulseTransactions) {
       this.pulseTransactions = pulseTransactions;
    }

    public void getTransactios(int code_status_transaction) {
        switch (code_status_transaction) {
            case 0:
                apiCall = clientCall.getService().getTransaction_pendings(Constant.USER_ID);
                break;
            case 1:
                apiCall = clientCall.getService().getTransaction_paids(Constant.USER_ID);
                break;
            case 2:
                apiCall = clientCall.getService().getTransaction_shippeds(Constant.USER_ID);
                break;
            default:
                return;
        }
        apiCall.enqueue(new Callback<Transaction>() {
            @Override
            public void onResponse(Call<Transaction> call, Response<Transaction> response) {
                listTransaction = response.body().getResponse();
                pulseTransactions.onSuccessGetTransactions(listTransaction);
            }

            @Override
            public void onFailure(Call<Transaction> call, Throwable t) {
                pulseTransactions.onFailOccureTransactions(t.getMessage());
            }
        });
    }
}
