package com.example.eunice.cryptconvert.REST;

import com.example.eunice.cryptconvert.MODEL.CryptoClass;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Eunice on 5/31/2018.
 */

public interface ApiInterface {

    @GET("pricemulti")
    Call<CryptoClass> getCurrency (@Query("fsyms") String Class, @Query("tsyms") String subClass);
}
