package com.example.eunice.cryptconvert.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.eunice.cryptconvert.data.db.CryptoCurrency;
import com.example.eunice.cryptconvert.data.network.ApiClient;
import com.example.eunice.cryptconvert.data.network.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CryptoConvertNetworkSourceImpl implements CryptoConvertNetworkSource {
    private ApiInterface apiInterface;

    public CryptoConvertNetworkSourceImpl(ApiInterface apiInterface) {
        this.apiInterface = apiInterface;
    }

    private MutableLiveData<CryptoCurrency> _downloadedCryptoCurrency = new MutableLiveData<>();

    @Override
    public LiveData<CryptoCurrency> downloadedCryptoRates() {
        return _downloadedCryptoCurrency;
    }

    /*
    *
    * cryptoClassCall.enqueue(new Callback<CryptoCurrency>>() {
            @Override
            public void onResponse(Call<List<CryptoCurrency>> call, Response<List<CryptoCurrency>> response) {
                int statusCode = response.code();
                switch (statusCode) {
                    case 200:
                        if (response.body() != null) {
                            cryptoClasses = response.body();
//                            BTC btc = response.body().getBTC();
//                            ETH eth = response.body().getETH();
//
//                            if (btc.getNGN() != null || eth.getNGN() != null) {
//                                textViewBtc.setText(String.format(btc.getNGN().toString(), getApplication()));
//                                textViewEth.setText(String.format(eth.getNGN().toString(), getApplication()));
//                            }
                        }
                }
            }
            @Override
            public void onFailure(Call<List<CryptoCurrency>> call, Throwable t) {
                Log.e(TAG, t.getLocalizedMessage());
            }
        });*/

    @Override
    public void getCryptocurrencyData(){

        Call<CryptoCurrency> CryptoCurrencyCall = apiInterface.getCurrency("BTC,ETH",
                "USD,EUR,NGN,ARS,AUD,GBP,JPY,ILS,CHF,QAR,GHS,AED,SAR,ZAR,TRY,INR,BRL,ARS,CAD,CNY,SGD");

        CryptoCurrencyCall.enqueue(new Callback<CryptoCurrency>() {
            @Override
            public void onResponse(@NonNull Call<CryptoCurrency> call, @NonNull Response<CryptoCurrency> response) {
                int statusCode = response.code();
                if (statusCode == 200) {
                    _downloadedCryptoCurrency.postValue(response.body());
                }else if (statusCode == 500){
                    Log.d(CryptoConvertNetworkSource.class.getSimpleName(), "Internal Server Error");
                }

            }

            @Override
            public void onFailure(@NonNull Call<CryptoCurrency> call, @NonNull Throwable t) {
                Log.v(CryptoConvertNetworkSource.class.getSimpleName(), "Error on crypto call", t);
            }
        });
    }
}
