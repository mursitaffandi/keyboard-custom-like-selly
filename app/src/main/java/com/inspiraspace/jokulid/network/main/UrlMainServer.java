package com.inspiraspace.jokulid.network.main;

import com.inspiraspace.jokulid.model.autotext.Mautotext;
import com.inspiraspace.jokulid.model.preaddtransaction.Premaketransaction;
import com.inspiraspace.jokulid.model.transactions.Transaction;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by mursitaffandi on 4/2/18.
 */

public interface UrlMainServer {
    @GET("transaction_pending.php?status=0")
    Call<Transaction> getTransaction_pendings(@Query("user_id") String id_user);

    @GET("transaction_pending.php?status=1")
    Call<Transaction> getTransaction_paids(@Query("user_id") String id_user);

    @GET("transaction_pending.php?status=2")
    Call<Transaction> getTransaction_shippeds(@Query("user_id") String id_user);

    @GET("transaction_pending.php?status=11")
    Call<Transaction> getTransaction_done(@Query("user_id") String id_user);

    @GET("transaction_pending.php?status=10")
    Call<Transaction> getTransaction_cancel(@Query("user_id") String id_user);


    @GET("pre_create_transaction.php")
    Call<Premaketransaction> getPreDetailTransaction(@Query("user_id") String id_user);

    @GET("autotext.php")
    Call<Mautotext> getAutoText(@Query("user_id") String id_user);
}
