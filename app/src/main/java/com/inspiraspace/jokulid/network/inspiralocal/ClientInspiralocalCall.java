package com.inspiraspace.jokulid.network.inspiralocal;

import com.inspiraspace.jokulid.BuildConfig;
import com.inspiraspace.jokulid.utils.Constant;

import java.io.IOException;

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

public class ClientInspiralocalCall {
    private UrlInspiralocal apiCall;

    public ClientInspiralocalCall() {

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();

                HttpUrl httpUrl = request.url().newBuilder()
/*
                        .addQueryParameter("api_key", BuildConfig.API_KEY)
*/
                        .build();

                request = request
                        .newBuilder()
                        .addHeader(Constant.HEADER_AUTHONRIZATION_KEY, Constant.HEADER_AUTHONRIZATION_VALUE)
                        .url(httpUrl)
                        .build();
                return chain.proceed(request);
            }
        }).build();

        Retrofit retrofit =  new Retrofit.Builder().client(okHttpClient).baseUrl(BuildConfig.BASE_URL_INSPIRALOCAL).addConverterFactory(GsonConverterFactory.create()).build();

        apiCall = retrofit.create(UrlInspiralocal.class);
    }

    public UrlInspiralocal getService() {
        return apiCall;
    }
}
