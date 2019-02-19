package com.example.eunice.cryptconvert;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.example.eunice.cryptconvert.MODEL.BTC;
import com.example.eunice.cryptconvert.MODEL.CryptoClass;
import com.example.eunice.cryptconvert.MODEL.ETH;

/**
 * Created by Eunice on 2/8/2019.
 */

@Entity(tableName = "Crypto")
public class Crypto {
    @PrimaryKey(autoGenerate = true)
    private int id;

    public CryptoClass getCrypto() {
        return crypto;
    }

    public void setCrypto(CryptoClass crypto) {
        this.crypto = crypto;
    }

    @ColumnInfo (name = "crypto")
    private CryptoClass crypto;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
