package com.example.eunice.cryptconvert.data.network;

import com.example.eunice.cryptconvert.data.db.CryptoCurrency;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Eunice on 5/31/2018.
 */

public interface ApiInterface {

    @GET("pricemulti")
    Call<CryptoCurrency> getCurrency (@Query("fsyms") String Class, @Query("tsyms") String subClass);
}
