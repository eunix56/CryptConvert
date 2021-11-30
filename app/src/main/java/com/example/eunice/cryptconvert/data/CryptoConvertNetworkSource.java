package com.example.eunice.cryptconvert.data;

import androidx.lifecycle.LiveData;

import com.example.eunice.cryptconvert.data.db.CryptoCurrency;


public interface CryptoConvertNetworkSource {

    LiveData<CryptoCurrency> downloadedCryptoRates();

    void getCryptocurrencyData();

}
