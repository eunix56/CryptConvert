package com.example.eunice.cryptconvert.data.utils;

import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;
import android.util.Log;


import com.example.eunice.cryptconvert.data.db.Country;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.exceptions.RealmException;

public class RealmUtils {

    private static RealmUtils mInstance;
    private final Realm mRealm;

    public RealmUtils(Application application) {
        this.mRealm = Realm.getDefaultInstance();
    }

    public static RealmUtils with(Fragment fragment) {

        if (mInstance == null) {
            mInstance = new RealmUtils(fragment.getActivity().getApplication());
        }
        return mInstance;
    }

    public static RealmUtils with(Activity activity) {

        if (mInstance == null) {
            mInstance = new RealmUtils(activity.getApplication());
        }
        return mInstance;
    }

    public static RealmUtils with(Application application) {

        if (mInstance == null) {
            mInstance = new RealmUtils(application);
        }
        return mInstance;
    }

    public static RealmUtils getInstance() {

        return mInstance;
    }

    public Realm getRealm() {

        return mRealm;
    }

    //Refresh the realm istance
    public void refresh() {

        mRealm.refresh();
    }

    public void clearAll() {
        try {
            mRealm.executeTransaction((mRealm) -> mRealm.delete(Country.class));
        }catch (RealmException e) {

        }
    }

    public RealmResults<Country> getCountries() {
        return mRealm.where(Country.class).findAll();
    }

    public Country getCountry(String countryName) {
        return mRealm.where(Country.class).equalTo("id", countryName).findFirst();
    }

    public void insertOrUpdateCountry(Country country, boolean isChecked) {
        try {
            mRealm.executeTransaction((mRealm) -> {
                country.setChecked(isChecked);
                mRealm.insertOrUpdate(country);
            });
        }catch (RealmException e) {
            Log.d(RealmUtils.class.getSimpleName(), e.getMessage());
        }
    }

    public void setAllCountriesUnchecked (List<Country> countryList) {
        try {
            mRealm.executeTransaction((mRealm) -> {
                for (Country country: countryList) {
                    country.setChecked(false);
                    mRealm.insertOrUpdate(country);
                }
            });
        }catch (RealmException e) {
            Log.d(RealmUtils.class.getSimpleName(), e.getMessage());
        }
    }


    public void insertOrUpdateDb(List<Country> countryList) {
        try {
            mRealm.executeTransaction((mRealm) -> {
                for (Country country: countryList) {
                    mRealm.insertOrUpdate(country);
                }
            });
        }catch (RealmException e) {
            Log.d(RealmUtils.class.getSimpleName(), e.getMessage());
        }
    }

   public void closeRealm() {
        mRealm.close();
   }

}
