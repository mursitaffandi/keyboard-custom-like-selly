
package com.inspiraspace.jokulid.model.rajaongkir;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Status {

    @SerializedName("code")
    private Long mCode;
    @SerializedName("description")
    private String mDescription;

    public Long getCode() {
        return mCode;
    }

    public void setCode(Long code) {
        mCode = code;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

}
