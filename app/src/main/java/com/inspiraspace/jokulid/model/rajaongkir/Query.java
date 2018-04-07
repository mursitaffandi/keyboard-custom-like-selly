
package com.inspiraspace.jokulid.model.rajaongkir;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Query {

    @SerializedName("courier")
    private String mCourier;
    @SerializedName("destination")
    private String mDestination;
    @SerializedName("destinationType")
    private String mDestinationType;
    @SerializedName("origin")
    private String mOrigin;
    @SerializedName("originType")
    private String mOriginType;
    @SerializedName("weight")
    private Long mWeight;

    public String getCourier() {
        return mCourier;
    }

    public void setCourier(String courier) {
        mCourier = courier;
    }

    public String getDestination() {
        return mDestination;
    }

    public void setDestination(String destination) {
        mDestination = destination;
    }

    public String getDestinationType() {
        return mDestinationType;
    }

    public void setDestinationType(String destinationType) {
        mDestinationType = destinationType;
    }

    public String getOrigin() {
        return mOrigin;
    }

    public void setOrigin(String origin) {
        mOrigin = origin;
    }

    public String getOriginType() {
        return mOriginType;
    }

    public void setOriginType(String originType) {
        mOriginType = originType;
    }

    public Long getWeight() {
        return mWeight;
    }

    public void setWeight(Long weight) {
        mWeight = weight;
    }

}
