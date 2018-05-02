package com.inspiraspace.jokulid.presenter;

import com.inspiraspace.jokulid.model.shop.ResponseShop;
import com.inspiraspace.jokulid.network.main.ClientMainCall;
import com.inspiraspace.jokulid.network.main.PulseShop;
import com.inspiraspace.jokulid.utils.Constant;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mursitaffandi on 4/26/18.
 */

public class GeneratorShop {
    PulseShop pulseShop;
    private ClientMainCall clientMainCall = new ClientMainCall();
    private Call<ResponseShop> apiCall;
    private Call<Void> apiUpdate;

    public GeneratorShop(PulseShop pulseShop) {
        this.pulseShop = pulseShop;
    }

    public void getShop(){
        apiCall = clientMainCall.getService().getShop(Constant.SESSION_USER_ID);
        apiCall.enqueue(new Callback<ResponseShop>() {
            @Override
            public void onResponse(Call<ResponseShop> call, retrofit2.Response<ResponseShop> response) {
                pulseShop.OnSuccessShop(response.body().getResponse());
            }

            @Override
            public void onFailure(Call<ResponseShop> call, Throwable t) {
                pulseShop.OnErrorShop(t.getMessage());
            }
        });
    }

    public void updateShop(String shop_name, String shop_nohp, String shop_url){
        apiUpdate = clientMainCall.getService().updateShop(Constant.SESSION_USER_ID,shop_name,shop_nohp, shop_url);
        apiUpdate.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                pulseShop.OnSuccessUpdateShop();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                pulseShop.OnErrorUpdateShop(t.getMessage());
            }
        });
    }
}
