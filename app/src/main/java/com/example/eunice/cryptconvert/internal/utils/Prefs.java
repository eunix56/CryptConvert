package com.example.eunice.cryptconvert.internal.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.icu.text.MessagePattern;
import android.preference.PreferenceManager;

import com.example.eunice.cryptconvert.data.db.Country;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class Prefs {
    private static Prefs prefs;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private final static String PREFS_NAME = "prefs_name";
    private final static String PREFS_STORE_COUNTRIES = "prefs_store_countries";
    private final static String PREFS_STORE_CLICKED_COUNTRIES = "prefs_store_clicked_countries";

    @SuppressLint("CommitPrefEdits")
    private Prefs(Context context) {
        pref = PreferenceManager.getDefaultSharedPreferences(context);
        editor = pref.edit();
    }

    public static void init(Context context) {
        prefs = new Prefs(context);
    }

    public static Prefs getInstance() {
        return prefs;
    }

    public void storeCountries(List<Country> countryList) {
        Gson gson = new Gson();
        String json = gson.toJson(countryList);
        editor.putString(PREFS_STORE_COUNTRIES, json);
        editor.commit();

    }

    public void storeClickedCountries(List<Country> countryList) {
        Gson gson = new Gson();
        String json = gson.toJson(countryList);
        editor.putString(PREFS_STORE_CLICKED_COUNTRIES, json);
        editor.commit();
    }

    public List<Country> getClickedCountries() {
        Gson gson = new Gson();
        String json = pref.getString(PREFS_STORE_CLICKED_COUNTRIES, null);
        Type type = new TypeToken<List<Country>>() {}.getType();
        return gson.fromJson(json, type);
    }

    public List<Country> getCountries() {
        Gson gson = new Gson();
        String json = pref.getString(PREFS_STORE_COUNTRIES, null);
        Type type = new TypeToken<List<Country>>() {}.getType();
        return gson.fromJson(json, type);
    }
}
