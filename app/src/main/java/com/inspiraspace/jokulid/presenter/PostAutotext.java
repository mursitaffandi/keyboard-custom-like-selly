package com.inspiraspace.jokulid.presenter;

import com.inspiraspace.jokulid.network.main.ClientMainCall;
import com.inspiraspace.jokulid.network.main.PulsePostAutotext;
import com.inspiraspace.jokulid.network.main.PulsePostBankAccount;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mursitaffandi on 4/25/18.
 */

public class PostAutotext {
    private ClientMainCall clientMainCall = new ClientMainCall();
    private Call<Void> apiCall;

    public PostAutotext(final PulsePostAutotext ppostAutotext,
                        String user_id,
                        String shortcut,
                        String content
    ) {
        apiCall = clientMainCall.getService().setNewAutotext(user_id, shortcut, content);
        apiCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                ppostAutotext.OnSuccessPostBankAccount();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                ppostAutotext.OnErrorPostBankAccount();
            }
        });
    }
}
