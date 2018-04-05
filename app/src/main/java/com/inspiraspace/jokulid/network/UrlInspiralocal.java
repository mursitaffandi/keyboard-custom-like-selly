package com.inspiraspace.jokulid.network;

import com.inspiraspace.jokulid.model.rajaongkir.Rajaongkir;
import com.inspiraspace.jokulid.model.searchsubdistrict.Findings;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by mursitaffandi on 4/4/18.
 */

public interface UrlInspiralocal {
    @GET("/provinces/search/{keyword}")
    Call<Findings> getFindingAddress(
            @Query("keyword") String keyword
    );
}
