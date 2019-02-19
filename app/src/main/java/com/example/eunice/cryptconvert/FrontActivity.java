package com.example.eunice.cryptconvert;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.eunice.cryptconvert.MODEL.BTC;
import com.example.eunice.cryptconvert.MODEL.CryptoClass;
import com.example.eunice.cryptconvert.MODEL.ETH;
import com.example.eunice.cryptconvert.REST.ApiInterface;
import com.example.eunice.cryptconvert.REST.CryptoViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FrontActivity extends LifecycleActivity{
    public static final String TAG = "FRONT_ACTIVITY";
    private RecyclerView cryptoRecycler;
    private ProgressBar progressBar;
    private FloatingActionButton cryptoFab;
    public static BTC btc;
    public static ETH eth;
    private Country country;
    private CryptoAdapter adapter;
    public CryptoViewModel cryptoViewModel;
    public static final String EXTRA_NAME = "country";
    public static final String EXTRA_ETH = "eth";

    ApiInterface apiInterface;

    @Nullable
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        cryptoFab = (FloatingActionButton)findViewById(R.id.cryptoFab);
        cryptoFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        cryptoViewModel = ViewModelProviders.of(this).get(CryptoViewModel.class);
        country = new Country();
        cryptoRecycler = (RecyclerView) findViewById(R.id.cryptoRecyclerView);
        cryptoRecycler.setLayoutManager(new LinearLayoutManager(this));
        cryptoViewModel.getCrypto().observe(this, new Observer<CryptoClass>() {
            @Override
            public void onChanged(@Nullable CryptoClass cryptoClass) {
                btc = cryptoClass.getBTC();
                eth = cryptoClass.getETH();
                adapter = new CryptoAdapter(FrontActivity.this, btc, eth, country, new CryptoAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View v, int position) {
                        Intent i = new Intent(FrontActivity.this, PressedActivity.class);
                        i.putExtra(EXTRA_ETH, position);
                        startActivity(i);
                    }
                });
                cryptoRecycler.setAdapter(adapter);
                progressBar.setVisibility(View.INVISIBLE);
            }
        });

    }

}
