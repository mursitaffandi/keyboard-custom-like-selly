
package com.inspiraspace.jokulid.model.rajaongkir;

import java.util.List;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Result {

    @SerializedName("code")
    private String mCode;
    @SerializedName("costs")
    private List<Cost> mCosts;
    @SerializedName("name")
    private String mName;

    public String getCode() {
        return mCode;
    }

    public void setCode(String code) {
        mCode = code;
    }

    public List<Cost> getCosts() {
        return mCosts;
    }

    public void setCosts(List<Cost> costs) {
        mCosts = costs;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

}
