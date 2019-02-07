package com.example.eunice.cryptconvert.MODEL;

import java.io.Serializable;
import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BTC implements Serializable, Parcelable

{

    ArrayList<Double> value;


    @SerializedName("USD")
    @Expose
    private Double uSD;
    @SerializedName("EUR")
    @Expose
    private Double eUR;
    @SerializedName("NGN")
    @Expose
    private Double nGN;
    @SerializedName("ARS")
    @Expose
    private Double aRS;
    @SerializedName("AUD")
    @Expose
    private Double aUD;
    @SerializedName("GBP")
    @Expose
    private Double gBP;
    @SerializedName("JPY")
    @Expose
    private Double jPY;
    @SerializedName("ILS")
    @Expose
    private Double iLS;
    @SerializedName("CHF")
    @Expose
    private Double cHF;
    @SerializedName("QAR")
    @Expose
    private Double qAR;
    @SerializedName("GHS")
    @Expose
    private Double gHS;
    @SerializedName("AED")
    @Expose
    private Double aED;
    @SerializedName("SAR")
    @Expose
    private Double sAR;
    @SerializedName("ZAR")
    @Expose
    private Double zAR;
    @SerializedName("TRY")
    @Expose
    private Double tRY;
    @SerializedName("INR")
    @Expose
    private Double iNR;
    @SerializedName("BRL")
    @Expose
    private Double bRL;
    @SerializedName("CAD")
    @Expose
    private Double cAD;
    @SerializedName("CNY")
    @Expose
    private Double cNY;
    @SerializedName("SGD")
    @Expose
    private Double sGD;
    public final static Parcelable.Creator<BTC> CREATOR = new Creator<BTC>() {


        @SuppressWarnings({
                "unchecked"
        })
        public BTC createFromParcel(Parcel in) {
            return new BTC(in);
        }

        public BTC[] newArray(int size) {
            return (new BTC[size]);
        }

    }
            ;
    private final static long serialVersionUID = -7009345447440322498L;

    protected BTC(Parcel in) {
        this.uSD = ((Double) in.readValue((Double.class.getClassLoader())));
        this.eUR = ((Double) in.readValue((Double.class.getClassLoader())));
        this.nGN = ((Double) in.readValue((Double.class.getClassLoader())));
        this.aRS = ((Double) in.readValue((Double.class.getClassLoader())));
        this.aUD = ((Double) in.readValue((Double.class.getClassLoader())));
        this.gBP = ((Double) in.readValue((Double.class.getClassLoader())));
        this.jPY = ((Double) in.readValue((Double.class.getClassLoader())));
        this.iLS = ((Double) in.readValue((Double.class.getClassLoader())));
        this.cHF = ((Double) in.readValue((Double.class.getClassLoader())));
        this.qAR = ((Double) in.readValue((Double.class.getClassLoader())));
        this.gHS = ((Double) in.readValue((Double.class.getClassLoader())));
        this.aED = ((Double) in.readValue((Double.class.getClassLoader())));
        this.sAR = ((Double) in.readValue((Double.class.getClassLoader())));
        this.zAR = ((Double) in.readValue((Double.class.getClassLoader())));
        this.tRY = ((Double) in.readValue((Double.class.getClassLoader())));
        this.iNR = ((Double) in.readValue((Double.class.getClassLoader())));
        this.bRL = ((Double) in.readValue((Double.class.getClassLoader())));
        this.cAD = ((Double) in.readValue((Double.class.getClassLoader())));
        this.cNY = ((Double) in.readValue((Double.class.getClassLoader())));
        this.sGD = ((Double) in.readValue((Double.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public BTC() {
    }


    /**
     *
     * @param uSD
     * @param aUD
     * @param tRY
     * @param cHF
     * @param gHS
     * @param iNR
     * @param eUR
     * @param sAR
     * @param zAR
     * @param sGD
     * @param aED
     * @param cNY
     * @param iLS
     * @param gBP
     * @param nGN
     * @param cAD
     * @param jPY
     * @param aRS
     * @param qAR
     * @param bRL
     */
    public BTC(Double uSD, Double eUR, Double nGN, Double aRS, Double aUD, Double gBP, Double jPY, Double iLS, Double cHF, Double qAR, Double gHS, Double aED, Double sAR, Double zAR, Double tRY, Double iNR, Double bRL, Double cAD, Double cNY, Double sGD) {
        super();
        this.uSD = uSD;
        this.eUR = eUR;
        this.nGN = nGN;
        this.aRS = aRS;
        this.aUD = aUD;
        this.gBP = gBP;
        this.jPY = jPY;
        this.iLS = iLS;
        this.cHF = cHF;
        this.qAR = qAR;
        this.gHS = gHS;
        this.aED = aED;
        this.sAR = sAR;
        this.zAR = zAR;
        this.tRY = tRY;
        this.iNR = iNR;
        this.bRL = bRL;
        this.cAD = cAD;
        this.cNY = cNY;
        this.sGD = sGD;
    }

    public void addArray(){
        value = new ArrayList<>();
        value.add(uSD);
        value.add(eUR);
        value.add(nGN);
        value.add(aRS);
        value.add(aUD);
        value.add(gBP);
        value.add(jPY);
        value.add(iLS);
        value.add(cHF);
        value.add(qAR);
        value.add(gHS);
        value.add(aED);
        value.add(sAR);
        value.add(zAR);
        value.add(tRY);
        value.add(iNR);
        value.add(bRL);
        value.add(cAD);
        value.add(cNY);
        value.add(sGD);
    }
    public double setArray(int position){
        return value.get(position);
    }

    public Double getUSD() {
        return uSD;
    }

    public void setUSD(Double uSD) {
        this.uSD = uSD;
    }

    public Double getEUR() {
        return eUR;
    }

    public void setEUR(Double eUR) {
        this.eUR = eUR;
    }

    public Double getNGN() {
        return nGN;
    }

    public void setNGN(Double nGN) {
        this.nGN = nGN;
    }

    public Double getARS() {
        return aRS;
    }

    public void setARS(Double aRS) {
        this.aRS = aRS;
    }

    public Double getAUD() {
        return aUD;
    }

    public void setAUD(Double aUD) {
        this.aUD = aUD;
    }

    public Double getGBP() {
        return gBP;
    }

    public void setGBP(Double gBP) {
        this.gBP = gBP;
    }

    public Double getJPY() {
        return jPY;
    }

    public void setJPY(Double jPY) {
        this.jPY = jPY;
    }

    public Double getILS() {
        return iLS;
    }

    public void setILS(Double iLS) {
        this.iLS = iLS;
    }

    public Double getCHF() {
        return cHF;
    }

    public void setCHF(Double cHF) {
        this.cHF = cHF;
    }

    public Double getQAR() {
        return qAR;
    }

    public void setQAR(Double qAR) {
        this.qAR = qAR;
    }

    public Double getGHS() {
        return gHS;
    }

    public void setGHS(Double gHS) {
        this.gHS = gHS;
    }

    public Double getAED() {
        return aED;
    }

    public void setAED(Double aED) {
        this.aED = aED;
    }

    public Double getSAR() {
        return sAR;
    }

    public void setSAR(Double sAR) {
        this.sAR = sAR;
    }

    public Double getZAR() {
        return zAR;
    }

    public void setZAR(Double zAR) {
        this.zAR = zAR;
    }

    public Double getTRY() {
        return tRY;
    }

    public void setTRY(Double tRY) {
        this.tRY = tRY;
    }

    public Double getINR() {
        return iNR;
    }

    public void setINR(Double iNR) {
        this.iNR = iNR;
    }

    public Double getBRL() {
        return bRL;
    }

    public void setBRL(Double bRL) {
        this.bRL = bRL;
    }

    public Double getCAD() {
        return cAD;
    }

    public void setCAD(Double cAD) {
        this.cAD = cAD;
    }

    public Double getCNY() {
        return cNY;
    }

    public void setCNY(Double cNY) {
        this.cNY = cNY;
    }

    public Double getSGD() {
        return sGD;
    }

    public void setSGD(Double sGD) {
        this.sGD = sGD;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(uSD);
        dest.writeValue(eUR);
        dest.writeValue(nGN);
        dest.writeValue(aRS);
        dest.writeValue(aUD);
        dest.writeValue(gBP);
        dest.writeValue(jPY);
        dest.writeValue(iLS);
        dest.writeValue(cHF);
        dest.writeValue(qAR);
        dest.writeValue(gHS);
        dest.writeValue(aED);
        dest.writeValue(sAR);
        dest.writeValue(zAR);
        dest.writeValue(tRY);
        dest.writeValue(iNR);
        dest.writeValue(bRL);
        dest.writeValue(cAD);
        dest.writeValue(cNY);
        dest.writeValue(sGD);
    }

    public int describeContents() {
        return 0;
    }

}
