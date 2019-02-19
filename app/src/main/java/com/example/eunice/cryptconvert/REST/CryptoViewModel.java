package com.example.eunice.cryptconvert.REST;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.example.eunice.cryptconvert.CryptoDao;
import com.example.eunice.cryptconvert.CryptoRepo;
import com.example.eunice.cryptconvert.FrontActivity;
import com.example.eunice.cryptconvert.MODEL.BTC;
import com.example.eunice.cryptconvert.MODEL.CryptoClass;
import com.example.eunice.cryptconvert.MODEL.ETH;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Eunice on 2/7/2019.
 */

public class CryptoViewModel extends ViewModel {


    private static CryptoRepo repo;
    private CryptoDao dao;
    private MediatorLiveData<CryptoClass> cryptoClass = new MediatorLiveData<>();

    public CryptoViewModel(CryptoDao dao){
        repo = new CryptoRepo(dao);
    }

    private void getData(){
        repo.fetchData();
    }

    public LiveData<CryptoClass> getCrypto(){
        return repo.getCryptoClass();
    }

}
