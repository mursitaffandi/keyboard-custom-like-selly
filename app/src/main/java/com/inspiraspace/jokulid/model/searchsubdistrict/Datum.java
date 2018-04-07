
package com.inspiraspace.jokulid.model.searchsubdistrict;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Datum {

    @SerializedName("city_id")
    private Long mCityId;
    @SerializedName("city_name")
    private String mCityName;
    @SerializedName("province_id")
    private Long mProvinceId;
    @SerializedName("province_name")
    private String mProvinceName;
    @SerializedName("subdistrict_id")
    private Long mSubdistrictId;
    @SerializedName("subdistrict_name")
    private String mSubdistrictName;

    public Long getCityId() {
        return mCityId;
    }

    public void setCityId(Long cityId) {
        mCityId = cityId;
    }

    public String getCityName() {
        return mCityName;
    }

    public void setCityName(String cityName) {
        mCityName = cityName;
    }

    public Long getProvinceId() {
        return mProvinceId;
    }

    public void setProvinceId(Long provinceId) {
        mProvinceId = provinceId;
    }

    public String getProvinceName() {
        return mProvinceName;
    }

    public void setProvinceName(String provinceName) {
        mProvinceName = provinceName;
    }

    public Long getSubdistrictId() {
        return mSubdistrictId;
    }

    public void setSubdistrictId(Long subdistrictId) {
        mSubdistrictId = subdistrictId;
    }

    public String getSubdistrictName() {
        return mSubdistrictName;
    }

    public void setSubdistrictName(String subdistrictName) {
        mSubdistrictName = subdistrictName;
    }

}
