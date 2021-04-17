package com.example.eunice.cryptconvert.data.utils;

import com.example.eunice.cryptconvert.data.db.BTC;
import com.example.eunice.cryptconvert.data.db.Country;
import com.example.eunice.cryptconvert.data.db.ETH;

import java.util.ArrayList;
import java.util.List;

public class CountryUtils {
    private List<Country> mCountryList = new ArrayList<>();


    public List<Country> getCountryList() {
        return mCountryList;
    }

    //"USD,EUR,NGN,ARS,AUD,GBP,JPY,ILS,CHF,QAR,GHS,AED,SAR,ZAR,TRY,INR,BRL,ARS,CAD,CNY,SGD"
    public void setCountryList(BTC btcValue, ETH ethValue) {
        if (btcValue != null && ethValue != null) {
            mCountryList.clear();
            mCountryList.add(createCountry("USD", btcValue.getUSD(), ethValue.getUSD()));
            mCountryList.add(createCountry("EUR", btcValue.getEUR(), ethValue.getEUR()));
            mCountryList.add(createCountry("NGN", btcValue.getNGN(), ethValue.getNGN()));
            mCountryList.add(createCountry("ARS", btcValue.getARS(), ethValue.getARS()));
            mCountryList.add(createCountry("AUD", btcValue.getAUD(), ethValue.getAUD()));
            mCountryList.add(createCountry("GBP", btcValue.getGBP(), ethValue.getGBP()));
            mCountryList.add(createCountry("JPY", btcValue.getJPY(), ethValue.getJPY()));
            mCountryList.add(createCountry("ILS", btcValue.getILS(), ethValue.getILS()));
            mCountryList.add(createCountry("CHF", btcValue.getCHF(), ethValue.getCHF()));
            mCountryList.add(createCountry("QAR", btcValue.getQAR(), ethValue.getQAR()));
            mCountryList.add(createCountry("GHS", btcValue.getGHS(), ethValue.getGHS()));
            mCountryList.add(createCountry("AED", btcValue.getAED(), ethValue.getAED()));
            mCountryList.add(createCountry("SAR", btcValue.getSAR(), ethValue.getSAR()));
            mCountryList.add(createCountry("ZAR", btcValue.getZAR(), ethValue.getZAR()));
            mCountryList.add(createCountry("TRY", btcValue.getTRY(), ethValue.getTRY()));
            mCountryList.add(createCountry("INR", btcValue.getINR(), ethValue.getINR()));
            mCountryList.add(createCountry("BRL", btcValue.getBRL(), ethValue.getBRL()));
            mCountryList.add(createCountry("ARS", btcValue.getARS(), ethValue.getARS()));
            mCountryList.add(createCountry("CAD", btcValue.getCAD(), ethValue.getCAD()));
            mCountryList.add(createCountry("CNY", btcValue.getCNY(), ethValue.getCNY()));
            mCountryList.add(createCountry("SGD", btcValue.getSGD(), ethValue.getSGD()));
        }

    }

    private Country createCountry(String countryName, Double btc, Double eth) {
        Country country = new Country();
        country.setName(countryName);
        country.setBtcValue(btc);
        country.setEthValue(eth);
        country.setChecked(false);

        return country;
    }
}
