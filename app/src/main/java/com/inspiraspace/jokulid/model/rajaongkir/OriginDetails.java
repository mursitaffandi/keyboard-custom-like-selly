
package com.inspiraspace.jokulid.model.rajaongkir;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class OriginDetails {

    @SerializedName("city")
    private String mCity;
    @SerializedName("city_id")
    private String mCityId;
    @SerializedName("province")
    private String mProvince;
    @SerializedName("province_id")
    private String mProvinceId;
    @SerializedName("subdistrict_id")
    private String mSubdistrictId;
    @SerializedName("subdistrict_name")
    private String mSubdistrictName;
    @SerializedName("type")
    private String mType;

    public String getCity() {
        return mCity;
    }

    public void setCity(String city) {
        mCity = city;
    }

    public String getCityId() {
        return mCityId;
    }

    public void setCityId(String cityId) {
        mCityId = cityId;
    }

    public String getProvince() {
        return mProvince;
    }

    public void setProvince(String province) {
        mProvince = province;
    }

    public String getProvinceId() {
        return mProvinceId;
    }

    public void setProvinceId(String provinceId) {
        mProvinceId = provinceId;
    }

    public String getSubdistrictId() {
        return mSubdistrictId;
    }

    public void setSubdistrictId(String subdistrictId) {
        mSubdistrictId = subdistrictId;
    }

    public String getSubdistrictName() {
        return mSubdistrictName;
    }

    public void setSubdistrictName(String subdistrictName) {
        mSubdistrictName = subdistrictName;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

}
