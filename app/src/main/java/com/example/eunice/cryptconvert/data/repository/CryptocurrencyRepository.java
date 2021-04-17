package com.example.eunice.cryptconvert.data.repository;

import android.arch.lifecycle.LiveData;

import com.example.eunice.cryptconvert.data.db.Country;
import com.example.eunice.cryptconvert.data.db.CryptoCurrency;

import java.util.List;

public interface CryptocurrencyRepository {

    LiveData<CryptoCurrency> getCryptoCurrencyData();

    List<Country> getCountryListData();
}
