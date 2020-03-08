package com.kritarthagrawal.coronamonitor.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public static final String BASE_URL = "https://coronavirus-monitor.p.rapidapi.com/coronavirus/";
    public static Retrofit retrofit;

    public static RetrofitAPI retrofitAPI;
    public static Retrofit getApiClient(){
        if (retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static RetrofitAPI  retrofitAPI(){
        if(retrofitAPI==null){
            retrofitAPI = getApiClient().create(RetrofitAPI.class);
        }

        return retrofitAPI;
    }
}