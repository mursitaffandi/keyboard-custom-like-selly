package com.inspiraspace.jokulid.network.main;

import com.inspiraspace.jokulid.model.autotext.Mautotext;
import com.inspiraspace.jokulid.model.bank.Bank;
import com.inspiraspace.jokulid.model.customers.Customers;
import com.inspiraspace.jokulid.model.login.ResponseLogin;
import com.inspiraspace.jokulid.model.preaddtransaction.Premaketransaction;
import com.inspiraspace.jokulid.model.shop.ResponseShop;
import com.inspiraspace.jokulid.model.template.Template;
import com.inspiraspace.jokulid.model.transactions.Transaction;
import com.inspiraspace.jokulid.model.user.ResponseUser;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
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
    Call<Mautotext> getAutoText(@Query("user_id") String id_user, @Query("keyword") String keyword);

    @GET("customers.php")
    Call<Customers> getCustomers(@Query("user_id") String id_user);

    @GET("get_template.php")
    Call<Template> getTemplate(@Query("user_id") String id_user, @Query("template") String template);

    @GET("get_bank.php")
    Call<Bank> getBank();

    @GET("get_shop.php")
    Call<ResponseShop> getShop(@Query("user_id") String id_user);

    @FormUrlEncoded
    @POST("update_shop.php")
    Call<Void> updateShop(
            @Field("user_id") String user_id,
            @Field("shop_name") String shop_name,
            @Field("shop_nohp") String shop_nohp,
            @Field("shop_url") String shop_url
    );

    @GET("get_user.php")
    Call<ResponseUser> getUser(@Query("user_id") String id_user);

    @FormUrlEncoded
    @POST("update_user.php")
    Call<Void> updateUser(
            @Field("user_name") String user_name,
            @Field("user_email") String user_email,
            @Field("user_password") String user_password,
            @Field("user_id") String user_id
    );

    @FormUrlEncoded
    @POST("add_transaction.php")
    Call<Void>  setNewTransaction(
            @Field("customer_name") String customer_name,
            @Field("customer_nohp") String customer_nohp,
            @Field("customer_address") String customer_address,
            @Field("transaction_note") String transaction_note,
            @Field("transaction_ongkir") String transaction_ongkir,
            @Field("bank_account_id") String bank_account_id,
            @Field("chatapp_id") String chatapp_id,
            @Field("item_qty") String item_qty,
            @Field("item_name") String item_name,
            @Field("item_price") String item_price,
            @Field("user_id") String user_id,
            @Field("toko_id") String toko_id
    );

    @FormUrlEncoded
    @POST("add_bank_account.php")
    Call<Void>  setNewBankAccount(
            @Field("user_id") String user_id,
            @Field("account_bank_id") String account_bank_id,
            @Field("account_number") String account_number,
            @Field("account_name") String account_name
    );

    @FormUrlEncoded
    @POST("add_autotext.php")
    Call<Void>  setNewAutotext(
            @Field("user_id")String user_id,
            @Field("shortcut")String shortcut,
            @Field("content")String content
    );

    @FormUrlEncoded
    @POST("update_template.php")
    Call<Void>  updateTemplate(
            @Field("user_id")String user_id,
            @Field("template")String template,
            @Field("new_content")String new_content
    );

    @FormUrlEncoded
    @POST("login.php")
    Call<ResponseLogin> login(
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("update_transaction_status.php")
    Call<Void>  updateTransactionStatus(
            @Field("user_id") String user_id,
            @Field("transaction_id") String transaction_id,
            @Field("new_status") String new_status
    );

    @FormUrlEncoded
    @POST("add_user.php")
    Call<Void>  setNewUser(
            @Field("user_email") String user_email,
            @Field("user_password") String user_password,
            @Field("user_name") String user_name,
            @Field("toko_name") String toko_name,
            @Field("toko_nohp") String toko_nohp,
            @Field("toko_url") String toko_url
    );
}
