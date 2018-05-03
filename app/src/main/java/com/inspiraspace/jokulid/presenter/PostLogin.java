package com.inspiraspace.jokulid.presenter;

import com.inspiraspace.jokulid.model.login.ResponseLogin;
import com.inspiraspace.jokulid.network.main.ClientMainCall;
import com.inspiraspace.jokulid.network.main.PulseLogin;
import com.inspiraspace.jokulid.preenter.LoginActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mursitaffandi on 4/30/18.
 */

public class PostLogin {
    private ClientMainCall clientMainCall = new ClientMainCall();
    private Call<ResponseLogin> apiCall;

    public PostLogin(final PulseLogin ppostLogin,
                        String email,
                        String password
    ) {
        apiCall = clientMainCall.getService().login(email,password);
        apiCall.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                ppostLogin.OnSuccessLogin(response.body().getResponse());
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                ppostLogin.OnErrorLogin(t.getMessage());
            }
        });
    }

}
