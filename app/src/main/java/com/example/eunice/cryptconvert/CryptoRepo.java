package com.example.eunice.cryptconvert;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;

import com.example.eunice.cryptconvert.MODEL.BTC;
import com.example.eunice.cryptconvert.MODEL.CryptoClass;
import com.example.eunice.cryptconvert.MODEL.ETH;
import com.example.eunice.cryptconvert.REST.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Eunice on 2/13/2019.
 */

public class CryptoRepo {

    private static final String BASE_URL = "https://min-api.cryptocompare.com/data/";
    private static Retrofit retrofit = null;
    private MutableLiveData<CryptoClass> cryptoClass;
    public CryptoDao cryptoDao;

    public CryptoRepo(CryptoDao dao) {
        this.cryptoDao = dao;
    }

    public void fetchData(){
        BTC btc = null;
        ETH eth = null;
        if (cryptoClass.getValue() != null){
            btc = cryptoClass.getValue().getBTC();
            eth = cryptoClass.getValue().getETH();
        }
        loadCryptoDB();
        getCryptoWeb();
    }

    private void loadCryptoDB() {
        new AsyncTask<Void, Void, CryptoClass>() {

            @Override
            protected CryptoClass doInBackground(Void... params) {
                return cryptoDao.cryptoDb();
            }

        }.execute();
    }

    public MutableLiveData<CryptoClass> getCryptoClass() {
        if (cryptoClass == null) {
            cryptoClass = new MutableLiveData<>();
        }
        return cryptoClass;
    }

    private void getCryptoWeb() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).
                    addConverterFactory(GsonConverterFactory.create()).build();

            ApiInterface api = retrofit.create(ApiInterface.class);
            Call<CryptoClass> cryptoClassCall = api.getCurrency("BTC,ETH", "USD,EUR,NGN,ARS,AUD,GBP,JPY,ILS,CHF,QAR,GHS,AED,SAR,ZAR,TRY,INR,BRL,ARS,CAD,CNY,SGD");

            cryptoClassCall.enqueue(new Callback<CryptoClass>() {
                @Override
                public void onResponse(Call<CryptoClass> call, Response<CryptoClass> response) {
                    int statusCode = response.code();
                    switch (statusCode) {
                        case 200:
                            if (response.body() != null) {
                                cryptoClass.setValue(response.body());
                                addDatatoDB(response.body());
                            }
                    }

                }

                @Override
                public void onFailure(Call<CryptoClass> call, Throwable t) {

                }
            });
        }

    }

    private void addDatatoDB(final CryptoClass crypto) {
        new AsyncTask<CryptoClass, Void, Boolean>() {

            @Override
            protected Boolean doInBackground(CryptoClass... params) {
                boolean needsUpdate = false;
                if (crypto != null) {
                    cryptoDao.insertCrypto(crypto);
                    needsUpdate = true;
                }
                return needsUpdate;
            }
            @Override
            protected void onPostExecute(Boolean needsUpdate) {
                if (needsUpdate) {
                    loadCryptoDB();
                }
            }
        }.execute(crypto);

    }





}
