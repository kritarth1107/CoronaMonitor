package com.kritarthagrawal.coronamonitor.network;

import com.kritarthagrawal.coronamonitor.ui.list.ListPojo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface RetrofitAPI {


    @GET("cases_by_country.php")
    Call<ListPojo> getList(
            @Header("x-rapidapi-host") String host,
            @Header("x-rapidapi-key") String key
    );
}
