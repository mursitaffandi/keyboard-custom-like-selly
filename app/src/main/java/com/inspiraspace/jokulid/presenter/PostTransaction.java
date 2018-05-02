package com.inspiraspace.jokulid.presenter;

import com.inspiraspace.jokulid.network.main.ClientMainCall;
import com.inspiraspace.jokulid.network.main.PulsePostTransaction;
import com.inspiraspace.jokulid.utils.Constant;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mursitaffandi on 4/30/18.
 */

public class PostTransaction {
    private ClientMainCall clientMainCall = new ClientMainCall();
    private Call<Void> apiCall;
    private PulsePostTransaction ppostTransaction;

    public void sendPostTransaction(
            String customer_name,
            String customer_nohp,
            String customer_address,
            String transaction_note,
            String transaction_ongkir,
            String bank_account_id,
            String chatapp_id,
            String item_qty,
            String item_name,
            String item_price
    ) {
        apiCall = clientMainCall.getService().setNewTransaction(
                customer_name,
                customer_nohp,
                customer_address,
                transaction_note,
                transaction_ongkir,
                bank_account_id,
                chatapp_id,
                item_qty,
                item_name,
                item_price,
                Constant.SESSION_USER_ID,
                Constant.SESSION_TOKO_ID
        );

        apiCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                ppostTransaction.OnSuccessAddTransaction();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                ppostTransaction.OnErrorAddTransaction(t.getMessage());
            }
        });
    }

    public PostTransaction(PulsePostTransaction ppostTransaction) {
        this.ppostTransaction = ppostTransaction;
    }
}
