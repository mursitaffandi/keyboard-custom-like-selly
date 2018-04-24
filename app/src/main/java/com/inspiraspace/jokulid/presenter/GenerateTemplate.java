package com.inspiraspace.jokulid.presenter;

import com.inspiraspace.jokulid.model.autotext.Mautotext;
import com.inspiraspace.jokulid.model.template.Template;
import com.inspiraspace.jokulid.network.main.ClientMainCall;
import com.inspiraspace.jokulid.network.main.PulseTemplate;
import com.inspiraspace.jokulid.utils.Constant;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by mursitaffandi on 4/24/18.
 */

public class GenerateTemplate {
    PulseTemplate pulseTemplate;
    private ClientMainCall clientMainCall = new ClientMainCall();
    private Call<Template> apiCall;

    public GenerateTemplate(PulseTemplate pulseTemplate) {
        this.pulseTemplate = pulseTemplate;
    }

    public void getTemplate(String template){
        apiCall = clientMainCall.getService().getTemplate(Constant.USER_ID, template);
        apiCall.enqueue(new Callback<Template>() {
            @Override
            public void onResponse(Call<Template> call, retrofit2.Response<Template> response) {
                pulseTemplate.OnSuccessTemplate(response.body().getResponse());
            }

            @Override
            public void onFailure(Call<Template> call, Throwable t) {
                pulseTemplate.OnErrorTemplate(t.getMessage());
            }
        });
    }
}
