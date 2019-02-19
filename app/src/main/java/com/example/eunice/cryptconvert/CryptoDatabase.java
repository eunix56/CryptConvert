package com.example.eunice.cryptconvert;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by Eunice on 2/8/2019.
 */

@Database(entities = {Crypto.class}, version=1)
public abstract class CryptoDatabase extends RoomDatabase {

    public abstract CryptoDao cryptoDao();

    private static CryptoDatabase INSTANCE;

    public static CryptoDatabase getDatabase(Context context){
        if (INSTANCE == null){
            synchronized (CryptoDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), CryptoDatabase.class, "crypto_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public static void destroyDatabase(){
        INSTANCE = null;
    }
}
