
package com.inspiraspace.jokulid.model.rajaongkir;

import java.util.List;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Cost {

    @SerializedName("cost")
    private List<Cost> mCost;
    @SerializedName("description")
    private String mDescription;
    @SerializedName("etd")
    private String mEtd;
    @SerializedName("note")
    private String mNote;
    @SerializedName("service")
    private String mService;
    @SerializedName("value")
    private Long mValue;

    public List<Cost> getCost() {
        return mCost;
    }

    public void setCost(List<Cost> cost) {
        mCost = cost;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getEtd() {
        return mEtd;
    }

    public void setEtd(String etd) {
        mEtd = etd;
    }

    public String getNote() {
        return mNote;
    }

    public void setNote(String note) {
        mNote = note;
    }

    public String getService() {
        return mService;
    }

    public void setService(String service) {
        mService = service;
    }

    public Long getValue() {
        return mValue;
    }

    public void setValue(Long value) {
        mValue = value;
    }

}
