
package com.inspiraspace.jokulid.model.searchsubdistrict;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Findings {

    @SerializedName("data")
    private List<Datum> mData;
    @SerializedName("message")
    private String mMessage;
    @SerializedName("meta")
    private Meta mMeta;
    @SerializedName("status")
    private Boolean mStatus;

    public List<Datum> getData() {
        return mData;
    }

    public ArrayList<Datum> getArrDatum(){
        ArrayList<Datum> p = new ArrayList<>();
        for (Datum mDatum : mData)
            p.add(mDatum);
        return p;
    }

    public ArrayList<String> getArrSubdistrict(){
        ArrayList<String> result = new ArrayList<>();
        for(Datum r: mData){
            result.add(r.getSubdistrictName() + ", " + r.getCityName() + ", " + r.getProvinceName());
        }
        return result;
    }

    public void setData(List<Datum> data) {
        mData = data;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public Meta getMeta() {
        return mMeta;
    }

    public void setMeta(Meta meta) {
        mMeta = meta;
    }

    public Boolean getStatus() {
        return mStatus;
    }

    public void setStatus(Boolean status) {
        mStatus = status;
    }

}
