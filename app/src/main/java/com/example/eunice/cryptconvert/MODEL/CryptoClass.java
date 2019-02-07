package com.example.eunice.cryptconvert.MODEL;

/**
 * Created by Eunice on 5/31/2018.
 */

import java.io.Serializable;
import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CryptoClass implements Serializable, Parcelable
{

    ArrayList<String> Country;
    @SerializedName("BTC")
    @Expose
    private BTC bTC;
    @SerializedName("ETH")
    @Expose
    private ETH eTH;
    public final static Parcelable.Creator<CryptoClass> CREATOR = new Creator<CryptoClass>() {


        @SuppressWarnings({
                "unchecked"
        })
        public CryptoClass createFromParcel(Parcel in) {
            return new CryptoClass(in);
        }

        public CryptoClass[] newArray(int size) {
            return (new CryptoClass[size]);
        }

    }
            ;
    private final static long serialVersionUID = 4202296566563349023L;

    protected CryptoClass(Parcel in) {
        this.bTC = ((BTC) in.readValue((BTC.class.getClassLoader())));
        this.eTH = ((ETH) in.readValue((ETH.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public CryptoClass() {
    }

    /**
     *
     * @param eTH
     * @param bTC
     */
    public CryptoClass(BTC bTC, ETH eTH) {
        super();
        this.bTC = bTC;
        this.eTH = eTH;
    }

    public BTC getBTC() {
        return bTC;
    }

    public void setBTC(BTC bTC) {
        this.bTC = bTC;
    }

    public ETH getETH() {
        return eTH;
    }

    public void setETH(ETH eTH) {
        this.eTH = eTH;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(bTC);
        dest.writeValue(eTH);
    }

    public int describeContents() {
        return 0;
    }

}
