package com.inspiraspace.jokulid.network.ongkir;

import com.inspiraspace.jokulid.model.rajaongkir.FeeShipping;
import com.inspiraspace.jokulid.model.rajaongkir.Rajaongkir;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by mursitaffandi on 4/4/18.
 */

public interface UrlRajaongkir {
    @FormUrlEncoded
    @POST("/api/cost")
    Call<FeeShipping> requestfee(
            @Field("origin") String origin,
            @Field("originType") String originType,
            @Field("destination") String destination,
            @Field("destinationType") String destinationType,
            @Field("weight") String weight,
            @Field("courier") String courier
    );
}
