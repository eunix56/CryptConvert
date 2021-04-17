package com.example.eunice.cryptconvert.data.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.example.eunice.cryptconvert.data.CryptoConvertNetworkSource;
import com.example.eunice.cryptconvert.data.utils.CountryUtils;
import com.example.eunice.cryptconvert.data.utils.RealmUtils;
import com.example.eunice.cryptconvert.data.db.Country;
import com.example.eunice.cryptconvert.data.db.CryptoCurrency;

import java.util.List;

public class CryptocurrencyRepositoryImpl implements CryptocurrencyRepository {
    private CryptoConvertNetworkSource cryptoConvertNetworkSource;
    private CountryUtils countryUtils;
    private RealmUtils realmUtils;

    public CryptocurrencyRepositoryImpl(Application application, CryptoConvertNetworkSource networkSource, CountryUtils utils) {
        cryptoConvertNetworkSource = networkSource;
        countryUtils = utils;
        realmUtils = new RealmUtils(application);

        cryptoConvertNetworkSource.downloadedCryptoRates().observeForever(this::storeCryptoValuesIntoCountry);
    }

    private void initCountryData() {
        List<Country> countries = realmUtils.getCountries();

        if (countries == null || countries.size() == 0) {
            cryptoConvertNetworkSource.getCryptocurrencyData();
        }
    }

    private void storeCryptoValuesIntoCountry(CryptoCurrency cryptoCurrency) {
       countryUtils.setCountryList(cryptoCurrency.getBTC(), cryptoCurrency.getETH());
       realmUtils.insertOrUpdateDb(countryUtils.getCountryList());
    }


    @Override
    public LiveData<CryptoCurrency> getCryptoCurrencyData() {
        initCountryData();
        return cryptoConvertNetworkSource.downloadedCryptoRates();
    }

    @Override
    public List<Country> getCountryListData() {
        initCountryData();
        return realmUtils.getCountries();
    }
}
