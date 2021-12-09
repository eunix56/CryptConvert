package com.example.eunice.cryptconvert.ui;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.appcompat.app.AppCompatActivity;

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
import com.example.eunice.cryptconvert.ui.viewmodel.CryptoConvertViewModel;
import com.example.eunice.cryptconvert.ui.viewmodel.CryptoConvertViewModelFactory;

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
                CryptoConvertFragment.class.getSimpleName());
    }

    private void startFragment(Fragment fragment, String tag) {
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.container, fragment, tag).commit();
    }

    private void startFragmentToBackStack(Fragment fragment, String tag) {
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.container, fragment, tag).addToBackStack(tag).commit();
    }

    private void startFragmentAnimation(Fragment fragment, String tag) {
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
        .setCustomAnimations(R.anim.slide_up_in, R.anim.slide_up_out)
                .add(R.id.container, fragment)
                .addToBackStack(tag)
                .commit();
    }

    private void refresh() {
        if (countries == null || countries.size() == 0) {
            viewModel.getCryptoData();
        }
    }

    @Override
    public void onStartFragment(Country country) {
        startFragmentAnimation(CryptoCardFragment.newInstance(country), CryptoCardFragment.class.getSimpleName());
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
