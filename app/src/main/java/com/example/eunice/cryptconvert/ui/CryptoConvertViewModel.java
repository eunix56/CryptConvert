package com.example.eunice.cryptconvert.ui;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.eunice.cryptconvert.data.db.Country;
import com.example.eunice.cryptconvert.data.db.CryptoCurrency;
import com.example.eunice.cryptconvert.data.repository.CryptocurrencyRepository;

import java.util.List;

public class CryptoConvertViewModel extends AndroidViewModel {
    private CryptocurrencyRepository repository;

    public CryptoConvertViewModel(
            @NonNull Application application,
            CryptocurrencyRepository mRepository) {
        super(application);
        repository = mRepository;
    }

    public List<Country> getCountries() {
        return repository.getCountryListData();
    }

    public LiveData<CryptoCurrency> getCryptoData() {
        return repository.getCryptoCurrencyData();
    }

}
