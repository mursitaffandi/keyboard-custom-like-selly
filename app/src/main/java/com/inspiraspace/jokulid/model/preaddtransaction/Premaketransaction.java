
package com.inspiraspace.jokulid.model.preaddtransaction;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Premaketransaction {

    @SerializedName("response")
    private Response mResponse;

    public Response getResponse() {
        return mResponse;
    }

    public void setResponse(Response response) {
        mResponse = response;
    }

}
