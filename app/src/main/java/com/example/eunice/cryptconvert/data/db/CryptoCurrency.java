package com.example.eunice.cryptconvert.data.db;

/**
 * Created by Eunice on 5/31/2018.
 */

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CryptoCurrency implements Serializable, Parcelable
{

    @SerializedName("BTC")
    @Expose
    private BTC bTC;
    @SerializedName("ETH")
    @Expose
    private ETH eTH;
    @Expose
    public final static Parcelable.Creator<CryptoCurrency> CREATOR = new Creator<CryptoCurrency>() {


        @SuppressWarnings({
                "unchecked"
        })
        public CryptoCurrency createFromParcel(Parcel in) {
            return new CryptoCurrency(in);
        }

        public CryptoCurrency[] newArray(int size) {
            return (new CryptoCurrency[size]);
        }

    }
            ;
    private final static long serialVersionUID = 4202296566563349023L;

    protected CryptoCurrency(Parcel in) {
        this.bTC = ((BTC) in.readValue((BTC.class.getClassLoader())));
        this.eTH = ((ETH) in.readValue((ETH.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public CryptoCurrency() {
    }

    /**
     *
     * @param eTH
     * @param bTC
     */
    public CryptoCurrency(BTC bTC, ETH eTH) {
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
