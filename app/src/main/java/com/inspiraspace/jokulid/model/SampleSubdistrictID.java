
package com.inspiraspace.jokulid.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import com.inspiraspace.jokulid.utils.Constant;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class SampleSubdistrictID {
    @SerializedName("response")
    private List<Response> mResponse;
    public List<Response> getResponse() {
        return mResponse;
    }

    public void setResponse(List<Response> response) {
        mResponse = response;
    }


    public ArrayList<String> getArrSubdistrict(){
        ArrayList<String> result = new ArrayList<>();
        for(Response r: mResponse){
            result.add(r.getSubdistrict() + ", " + r.getCity() + ", " + r.getState());
        }
        return result;
    }
}
