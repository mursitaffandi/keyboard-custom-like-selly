
package com.inspiraspace.jokulid.model.autotext;

import java.util.List;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Mautotext {

    @SerializedName("response")
    private List<Response> mResponse;

    public List<Response> getResponse() {
        return mResponse;
    }

    public void setResponse(List<Response> response) {
        mResponse = response;
    }

}
