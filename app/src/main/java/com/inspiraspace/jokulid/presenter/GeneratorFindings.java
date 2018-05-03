package com.inspiraspace.jokulid.presenter;

import android.widget.AutoCompleteTextView;

import com.inspiraspace.jokulid.model.searchsubdistrict.Findings;
import com.inspiraspace.jokulid.model.transactions.Log;
import com.inspiraspace.jokulid.network.inspiralocal.ClientInspiralocalCall;
import com.inspiraspace.jokulid.network.inspiralocal.PulseFindingsSubdistrict;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mursitaffandi on 4/4/18.
 */

public class GeneratorFindings {
    private PulseFindingsSubdistrict pulseMainServer;
    private ClientInspiralocalCall clientMainCall = new ClientInspiralocalCall();
    private Call<Findings> apiCall;

    public GeneratorFindings(PulseFindingsSubdistrict pulseMainServer) {
        this.pulseMainServer = pulseMainServer;
    }

    public void searchSubdistrict(String keyword, final AutoCompleteTextView field) {
        apiCall = clientMainCall.getService().getFindingAddress(keyword);
        apiCall.enqueue(new Callback<Findings>() {

            @Override
            public void onResponse(Call<Findings> call, Response<Findings> response) {
                Findings f = response.body();
                pulseMainServer.onSuccessFindingsSubdistrict(f, field);
            }

            @Override
            public void onFailure(Call<Findings> call, Throwable t) {
                pulseMainServer.onFailFindins(t.getMessage());
            }
        });
    }
}
