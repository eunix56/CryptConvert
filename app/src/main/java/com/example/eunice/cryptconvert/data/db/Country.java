package com.example.eunice.cryptconvert.data.db;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Country extends RealmObject {

    @PrimaryKey
    @Required
    private String name;
    private Double btcValue;
    private Double ethValue;
    private boolean checked;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getBtcValue() {
        return btcValue;
    }

    public void setBtcValue(Double btcValue) {
        this.btcValue = btcValue;
    }

    public Double getEthValue() {
        return ethValue;
    }

    public void setEthValue(Double ethValue) {
        this.ethValue = ethValue;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

}
