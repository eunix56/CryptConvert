package com.example.eunice.cryptconvert.data;

import android.arch.lifecycle.LiveData;

import com.example.eunice.cryptconvert.data.db.Country;
import com.example.eunice.cryptconvert.data.db.CryptoCurrency;


public interface CryptoConvertNetworkSource {

    LiveData<CryptoCurrency> downloadedCryptoRates();

    void getCryptocurrencyData();

}
