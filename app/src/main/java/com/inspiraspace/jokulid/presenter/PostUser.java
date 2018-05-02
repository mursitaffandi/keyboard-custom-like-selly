package com.inspiraspace.jokulid.presenter;

import com.inspiraspace.jokulid.network.main.ClientMainCall;
import com.inspiraspace.jokulid.network.main.PulsePostUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mursitaffandi on 4/30/18.
 */

public class PostUser {
    private ClientMainCall clientMainCall = new ClientMainCall();
    private Call<Void> apiCall;

    public PostUser(final PulsePostUser ppostUser,
                    String user_email,
                    String user_password,
                    String user_name,
                    String toko_name,
                    String toko_nohp,
                    String toko_url
    ) {
        apiCall = clientMainCall.getService().setNewUser(
                user_email,
                user_password,
                user_name,
                toko_name,
                toko_nohp,
                toko_url
        );

        apiCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                ppostUser.OnSuccessPostUser();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                ppostUser.OnErrorPostuser(t.getMessage());
            }
        });
    }
}
