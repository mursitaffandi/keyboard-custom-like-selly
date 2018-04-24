package com.inspiraspace.jokulid.network.ongkir;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.inspiraspace.jokulid.BuildConfig;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mursitaffandi on 4/4/18.
 */

public class ClientRajaongkirproCall {
    private UrlRajaongkir apiCall;

    public ClientRajaongkirproCall() {

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addNetworkInterceptor(new StethoInterceptor()).addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();

                HttpUrl httpUrl = request.url().newBuilder().build();

                request = request
                        .newBuilder()
                        .addHeader("key", "cb5107e448d1c4f1b73d57305e1cea18")
                        .addHeader("android-key", "0577fd5b7efd364c7de4928c07ec1376a51b08bb;com.inspiraspace.itwkeyboard")
                        .addHeader("Content-Type", "application/x-www-form-urlencoded")
                        .url(httpUrl)
                        .build();
                return chain.proceed(request);
            }
        }).readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit =  new Retrofit.Builder().client(okHttpClient).baseUrl(BuildConfig.BASE_URL_RAJAONGKIR).addConverterFactory(GsonConverterFactory.create()).build();

        apiCall = retrofit.create(UrlRajaongkir.class);
    }

    public UrlRajaongkir getService() {
        return apiCall;
    }
}