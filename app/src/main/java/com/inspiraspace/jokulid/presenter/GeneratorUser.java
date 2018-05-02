package com.inspiraspace.jokulid.presenter;


import com.inspiraspace.jokulid.model.user.ResponseUser;
import com.inspiraspace.jokulid.network.main.ClientMainCall;
import com.inspiraspace.jokulid.network.main.PulseUser;
import com.inspiraspace.jokulid.utils.Constant;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mursitaffandi on 4/26/18.
 */

public class GeneratorUser {
    PulseUser pulseUser;
    private ClientMainCall clientMainCall = new ClientMainCall();
    private Call<ResponseUser> userCall;
    private Call<Void> updateUserCall;

    public GeneratorUser(PulseUser pulseUser) {
        this.pulseUser = pulseUser;
    }

    public void getUser() {
        userCall = clientMainCall.getService().getUser(Constant.SESSION_USER_ID);
        userCall.enqueue(new Callback<ResponseUser>() {
            @Override
            public void onResponse(Call<ResponseUser> call, Response<ResponseUser> response) {
                pulseUser.OnSuccessUser(response.body().getResponse());
            }

            @Override
            public void onFailure(Call<ResponseUser> call, Throwable t) {
                pulseUser.OnErrorUser(t.getMessage());
            }
        });
    }

    public void updateUser(String name, String email, String password) {
        updateUserCall = clientMainCall.getService().updateUser(name, email, password, Constant.SESSION_USER_ID);
        updateUserCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                pulseUser.OnSuccessUpdateUser();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                pulseUser.OnErrorUpdateUser(t.getMessage());
            }
        });
    }
}
