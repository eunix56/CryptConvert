package com.example.eunice.cryptconvert;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.eunice.cryptconvert.MODEL.BTC;
import com.example.eunice.cryptconvert.MODEL.CryptoClass;
import com.example.eunice.cryptconvert.MODEL.ETH;
import com.example.eunice.cryptconvert.REST.ApiClient;
import com.example.eunice.cryptconvert.REST.ApiInterface;

import java.util.Map;
import java.util.Set;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FrontActivity extends AppCompatActivity {
    public static final String TAG = "FRONT_ACTIVITY";
    private RecyclerView cryptoRecycler;
    private ProgressBar progressBar;
    public static BTC btc;
    public static ETH eth;
    private Country country;
    public static final String EXTRA_NAME = "country";
    public static final String EXTRA_ETH = "eth";

    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front);

        country = new Country();
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        cryptoRecycler = (RecyclerView)findViewById(R.id.cryptoRecyclerView);
        cryptoRecycler.setLayoutManager(new LinearLayoutManager(this));

        getData();

    }


    public void getData() {
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<CryptoClass> cryptoClassCall = apiInterface.getCurrency("BTC,ETH", "USD,EUR,NGN,ARS,AUD,GBP,JPY,ILS,CHF,QAR,GHS,AED,SAR,ZAR,TRY,INR,BRL,ARS,CAD,CNY,SGD");
        cryptoClassCall.enqueue(new Callback<CryptoClass>() {
            @Override
            public void onResponse(Call<CryptoClass> call, Response<CryptoClass> response) {
                int statusCode = response.code();
                switch (statusCode) {
                    case 200:
                        if (response.body() != null) {
                            btc = response.body().getBTC();
                            eth = response.body().getETH();

                            CryptoAdapter Adapter = new CryptoAdapter(getApplicationContext(), btc, eth, country, new CryptoAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(View v, int position) {
                                    Intent i = new Intent(FrontActivity.this, PressedActivity.class);
                                    i.putExtra(EXTRA_ETH, position);
                                    startActivity(i);
                                }
                            });
                            cryptoRecycler.setAdapter(Adapter);
                            progressBar.setVisibility(View.INVISIBLE);
                        }
                }
            }

            @Override
            public void onFailure(Call<CryptoClass> call, Throwable t) {
                Log.e(TAG, t.getLocalizedMessage());
            }
        });


    }


}
