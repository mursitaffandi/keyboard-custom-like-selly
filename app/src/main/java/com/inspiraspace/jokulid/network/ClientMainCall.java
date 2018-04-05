package com.inspiraspace.jokulid.network;

import com.inspiraspace.jokulid.BuildConfig;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mursitaffandi on 4/2/18.
 */

public class ClientMainCall {
    private UrlMainServer apiCall;

    public ClientMainCall() {

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                HttpUrl httpUrl = request.url().newBuilder()
/*
                        .addQueryParameter("api_key", BuildConfig.API_KEY)
*/
                        .build();

                request = request.newBuilder().url(httpUrl).build();
                return chain.proceed(request);
            }
        }).build();

        Retrofit retrofit =  new Retrofit.Builder().client(okHttpClient).baseUrl(BuildConfig.BASE_URL_MAIN).addConverterFactory(GsonConverterFactory.create()).build();

        apiCall = retrofit.create(UrlMainServer.class);
    }

    public UrlMainServer getService() {
        return apiCall;
    }
}
