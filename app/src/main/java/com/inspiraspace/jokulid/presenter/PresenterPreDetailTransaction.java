package com.inspiraspace.jokulid.presenter;

import com.inspiraspace.jokulid.model.preaddtransaction.Premaketransaction;
import com.inspiraspace.jokulid.model.preaddtransaction.Response;
import com.inspiraspace.jokulid.network.main.ClientMainCall;
import com.inspiraspace.jokulid.utils.Constant;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by mursitaffandi on 4/7/18.
 */

public class PresenterPreDetailTransaction {
    private ClientMainCall clientMainCall = new ClientMainCall();
    private Call<Premaketransaction> apiCall;
    private Response listTransaction = new Response();
    private PulsePreDetailTransaction pulsePreDetailTransaction;

    public PresenterPreDetailTransaction(final PulsePreDetailTransaction pulsePreDetailTransaction) {
        this.pulsePreDetailTransaction = pulsePreDetailTransaction;

        apiCall = clientMainCall.getService().getPreDetailTransaction(Constant.USER_ID);
        apiCall.enqueue(new Callback<Premaketransaction>() {

            @Override
            public void onResponse(Call<Premaketransaction> call, retrofit2.Response<Premaketransaction> response) {
                listTransaction = response.body().getResponse();
                pulsePreDetailTransaction.onSuccesPayment(listTransaction);
            }

            @Override
            public void onFailure(Call<Premaketransaction> call, Throwable t) {
                pulsePreDetailTransaction.onFailure(t.getMessage());
            }
        });
    }


    public interface PulsePreDetailTransaction{
        void onSuccesPayment(Response payments);
        void onFailure(String message);
    }
}
