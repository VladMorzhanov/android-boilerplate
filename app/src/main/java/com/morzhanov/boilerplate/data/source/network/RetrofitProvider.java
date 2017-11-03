package com.morzhanov.boilerplate.data.source.network;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class RetrofitProvider {
    private static APIService mMevicsAPIService;

    static {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://mevics.com:3300/api/v2/")
//                .baseUrl("http://192.168.1.29:3300/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        mMevicsAPIService = retrofit.create(APIService.class);
    }

    static APIService getMevicsAPIService() {
        return mMevicsAPIService;
    }
}
