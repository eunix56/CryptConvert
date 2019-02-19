package com.example.eunice.cryptconvert;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.eunice.cryptconvert.MODEL.CryptoClass;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by Eunice on 2/8/2019.
 */

@Dao
public interface CryptoDao {
    @Insert(onConflict = REPLACE)
    void insertCrypto(CryptoClass cryptoClass);


    @Query("SELECT * FROM cryptoClass")
    CryptoClass cryptoDb();

    @Query("SELECT * FROM cryptoClass WHERE id=:id")
    CryptoClass crypto(int id);


    @Delete
    void deleteCrypto(int id);



}
