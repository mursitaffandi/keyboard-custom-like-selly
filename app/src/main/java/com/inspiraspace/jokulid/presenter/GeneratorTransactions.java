package com.inspiraspace.jokulid.presenter;

import com.inspiraspace.jokulid.model.transactions.Transaction;
import com.inspiraspace.jokulid.network.main.ClientMainCall;
import com.inspiraspace.jokulid.network.main.PulseMainServer;
import com.inspiraspace.jokulid.utils.Constant;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mursitaffandi on 4/2/18.
 */

public class GeneratorTransactions {
    PulseMainServer pulseMainServer;
    ClientMainCall clientMainCall = new ClientMainCall();
    private Call<Transaction> apiCall;
    private List<com.inspiraspace.jokulid.model.transactions.Response> listTransaction;
    int code_status_transaction;

    public GeneratorTransactions(PulseMainServer pulseMainServer) {
       this.pulseMainServer = pulseMainServer;
    }

    public void getTransactios(int code_status_transaction) {
        switch (code_status_transaction) {
            case 0:
                apiCall = clientMainCall.getService().getTransaction_pendings(Constant.USER_ID);
                break;
            case 1:
                apiCall = clientMainCall.getService().getTransaction_paids(Constant.USER_ID);
                break;
            case 2:
                apiCall = clientMainCall.getService().getTransaction_shippeds(Constant.USER_ID);
                break;

            case 3:
                apiCall = clientMainCall.getService().getTransaction_done(Constant.USER_ID);
                break;
            case 4:
                apiCall = clientMainCall.getService().getTransaction_cancel(Constant.USER_ID);
                break;
            default:
                return;
        }
        apiCall.enqueue(new Callback<Transaction>() {
            @Override
            public void onResponse(Call<Transaction> call, Response<Transaction> response) {
                listTransaction = response.body().getResponse();
                pulseMainServer.onSuccessGetTransactions(listTransaction);
            }

            @Override
            public void onFailure(Call<Transaction> call, Throwable t) {
                pulseMainServer.onFailOccureTransactions(t.getMessage());
            }
        });
    }
}
