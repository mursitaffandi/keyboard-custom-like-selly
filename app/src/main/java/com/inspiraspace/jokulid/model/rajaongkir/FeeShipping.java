
package com.inspiraspace.jokulid.model.rajaongkir;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class FeeShipping {

    @SerializedName("rajaongkir")
    private Rajaongkir mRajaongkir;

    public Rajaongkir getRajaongkir() {
        return mRajaongkir;
    }

    public void setRajaongkir(Rajaongkir rajaongkir) {
        mRajaongkir = rajaongkir;
    }

}
