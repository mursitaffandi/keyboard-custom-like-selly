package com.inspiraspace.jokulid.presenter;

import android.widget.AutoCompleteTextView;

import com.inspiraspace.jokulid.model.searchsubdistrict.Findings;
import com.inspiraspace.jokulid.network.inspiralocal.ClientInspiralocalCall;
import com.inspiraspace.jokulid.network.inspiralocal.PulseFindingsSubdistrict;
import com.inspiraspace.jokulid.network.main.ClientMainCall;
import com.inspiraspace.jokulid.network.main.PulsePostBankAccount;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mursitaffandi on 4/25/18.
 */

public class PostBankAccount {
    private ClientMainCall clientMainCall = new ClientMainCall();
    private Call<Void> apiCall;

    public PostBankAccount(final PulsePostBankAccount ppostBankAccount, String user_id,
                           String account_bank_id,
                           String account_number,
                           String account_name) {
        apiCall = clientMainCall.getService().setNewBankAccount(user_id, account_bank_id, account_number, account_name);
        apiCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                ppostBankAccount.OnSuccessPostBankAccount();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                ppostBankAccount.OnErrorPostBankAccount();
            }
        });
    }
}
