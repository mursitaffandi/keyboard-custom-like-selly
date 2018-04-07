
package com.inspiraspace.jokulid.model.preaddtransaction;

import com.google.gson.annotations.SerializedName;
import com.inspiraspace.jokulid.presenter.PresenterPreDetailTransaction;

import java.util.List;

@SuppressWarnings("unused")
public class PremakeTransaction {

    @SerializedName("response")
    private Response mResponse;

    public Response getResponse() {
        return mResponse;
    }

    public void setResponse(Response response) {
        mResponse = response;
    }

    public List<Payment> getPayment() {
        return new PresenterPreDetailTransaction().getPreaddTransaction();
    }

}
