package com.inspiraspace.jokulid.presenter;

import com.inspiraspace.jokulid.model.preaddtransaction.Payment;
import com.inspiraspace.jokulid.model.preaddtransaction.PremakeTransaction;
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
    ClientMainCall clientMainCall = new ClientMainCall();
    private Call<PremakeTransaction> apiCall;
    private Response listTransaction = new Response();
    private boolean isRequested = false;

    public PresenterPreDetailTransaction() {
        apiCall = clientMainCall.getService().getPreDetailTransaction(Constant.USER_ID);
        apiCall.enqueue(new Callback<PremakeTransaction>() {

            @Override
            public void onResponse(Call<PremakeTransaction> call, retrofit2.Response<PremakeTransaction> response) {
                listTransaction = response.body().getResponse();
                isRequested = true;
            }

            @Override
            public void onFailure(Call<PremakeTransaction> call, Throwable t) {
            }
        });
    }

    public boolean statusRequest() {
        return this.isRequested;
    }

    public List<Payment> getPreaddTransaction() {
        return listTransaction.getPayment();
    }

}
