
package com.inspiraspace.jokulid.model.searchsubdistrict;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Response {

    @SerializedName("city")
    private String mCity;
    @SerializedName("id")
    private String mId;
    @SerializedName("state")
    private String mState;
    @SerializedName("subdistrict")
    private String mSubdistrict;

    private String complateAddress;

    public String getComplateAddress(){
        return mSubdistrict + ", " + mCity + ", " + mState;
    }
    public String getCity() {
        return mCity;
    }

    public void setCity(String city) {
        mCity = city;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getState() {
        return mState;
    }

    public void setState(String state) {
        mState = state;
    }

    public String getSubdistrict() {
        return mSubdistrict;
    }

    public void setSubdistrict(String subdistrict) {
        mSubdistrict = subdistrict;
    }

}
