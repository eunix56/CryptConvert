package com.example.eunice.cryptconvert;

import java.util.ArrayList;

/**
 * Created by Eunice on 1/2/2019.
 */

public class Country {
    private ArrayList<String> Country;

    public int size(){
        return Country.size();
    }

    public void setCountry(){
        Country = new ArrayList<>();
        Country.add("USD");
        Country.add("EUR");
        Country.add("NGN");
        Country.add("ARS");
        Country.add("AUD");
        Country.add("GBP");
        Country.add("JPY");
        Country.add("ILS");
        Country.add("CHF");
        Country.add("QAR");
        Country.add("GHS");
        Country.add("AED");
        Country.add("SAR");
        Country.add("ZAR");
        Country.add("TRY");
        Country.add("INR");
        Country.add("BRL");
        Country.add("CAD");
        Country.add("CNY");
        Country.add("SGD");
    }

    public String getCountry(int position){
        return Country.get(position);
    }

}
