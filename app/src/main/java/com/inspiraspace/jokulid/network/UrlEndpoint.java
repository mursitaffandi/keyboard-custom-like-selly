package com.inspiraspace.jokulid.network;

import com.inspiraspace.jokulid.model.Response;
import com.inspiraspace.jokulid.model.Transaction;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by mursitaffandi on 4/2/18.
 */

public interface UrlEndpoint {
    @GET("transaction_pending.php")
    Call<Transaction> getTransaction_pendings(@Query("user_id") String id_user);

    @GET("transaction_paid.php")
    Call<Transaction> getTransaction_paids(@Query("user_id") String id_user);

    @GET("transaction_shipped.php")
    Call<Transaction> getTransaction_shippeds(@Query("user_id") String id_user);

    @GET("transaction_done.php")
    Call<Transaction> getTransaction_done(@Query("user_id") String id_user);

    @GET("transaction_cancel.php")
    Call<Transaction> getTransaction_cancel(@Query("user_id") String id_user);

}
