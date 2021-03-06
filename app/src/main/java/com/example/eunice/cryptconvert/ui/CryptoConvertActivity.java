package com.example.eunice.cryptconvert.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.eunice.cryptconvert.R;
import com.example.eunice.cryptconvert.data.CryptoConvertNetworkSource;
import com.example.eunice.cryptconvert.data.CryptoConvertNetworkSourceImpl;
import com.example.eunice.cryptconvert.data.db.Country;
import com.example.eunice.cryptconvert.data.network.ApiClient;
import com.example.eunice.cryptconvert.data.network.ApiInterface;
import com.example.eunice.cryptconvert.data.repository.CryptocurrencyRepository;
import com.example.eunice.cryptconvert.data.repository.CryptocurrencyRepositoryImpl;
import com.example.eunice.cryptconvert.data.utils.CountryUtils;
import com.example.eunice.cryptconvert.data.utils.RealmUtils;
import com.example.eunice.cryptconvert.internal.utils.Prefs;

import java.util.ArrayList;
import java.util.List;


public class CryptoConvertActivity extends AppCompatActivity implements CryptoConvertFragment.StartFragmentListener{

    private CryptoConvertViewModel viewModel;
    private CryptocurrencyRepository repository;
    private CryptoConvertNetworkSource networkSource;
    private ApiInterface apiInterface;
    private CountryUtils utils;
    private List<Country> countries;
    private RealmUtils realmUtils;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crypto_convert);

        Prefs.init(this);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        networkSource = new CryptoConvertNetworkSourceImpl(apiInterface);
        utils = new CountryUtils();
        realmUtils = RealmUtils.with(this.getApplication());
        repository = new CryptocurrencyRepositoryImpl(this.getApplication(), networkSource, utils);

        viewModel = new CryptoConvertViewModelFactory(this.getApplication(), repository).create(CryptoConvertViewModel.class);
        viewModel.getCryptoData().observe(this, cryptoCurrency -> {
            setUI();
        });

        setSwipeRefreshLayout();
        setUI();
    }


    private void setSwipeRefreshLayout() {
        swipeRefreshLayout = findViewById(R.id.swipe_layout);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            realmUtils.clearAll();
            refresh();
            swipeRefreshLayout.setRefreshing(false);
        });
    }

    private void setUI() {
        if (realmUtils.getCountries() == null || realmUtils.getCountries().size() == 0) refresh();
        if (Prefs.getInstance().getCountries() == null || Prefs.getInstance().getCountries().size() == 0)
            Prefs.getInstance().storeCountries(getCopyRealmCountries());
        bindUI();
    }

    private List<Country> getCopyRealmCountries() {
        List<Country> countries = new ArrayList<>();

        for (Country country: realmUtils.getCountries()){
            countries.add(realmUtils.getRealm().copyFromRealm(country));
        }

        return countries;
    }


    private void bindUI() {
        countries = Prefs.getInstance().getCountries();
        startFragment(CryptoConvertFragment.newInstance(this, countries, realmUtils),
                "crypto_convert_fragment");
    }

    private void startFragment(Fragment fragment, String tag) {
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.container, fragment, tag).commit();
    }

    private void startFragmentToBackStack(Fragment fragment, String tag) {
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.container, fragment, tag).addToBackStack(tag).commit();
    }

    private void refresh() {
        if (countries == null || countries.size() == 0) {
            viewModel.getCryptoData();
        }
    }

    @Override
    public void onStartFragment(Country country) {
        startFragmentToBackStack(CryptoCardFragment.newInstance(country), "crypto_card_fragment");
    }

    @Override
    public void onRefreshCountries() {
        refresh();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realmUtils.closeRealm();
    }
}
