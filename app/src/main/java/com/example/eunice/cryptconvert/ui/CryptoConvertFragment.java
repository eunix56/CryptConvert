package com.example.eunice.cryptconvert.ui;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.example.eunice.cryptconvert.R;
import com.example.eunice.cryptconvert.data.db.Country;
import com.example.eunice.cryptconvert.data.utils.RealmUtils;
import com.example.eunice.cryptconvert.ui.adapter.CryptoCardAdapter;
import com.example.eunice.cryptconvert.ui.adapter.CurrencyItemAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CryptoConvertFragment extends Fragment
        implements CryptoCardAdapter.OpenFragmentListener,
        CurrencyItemAdapter.OnItemClickedListener{

    private RecyclerView rvCryptoCard;
    private RecyclerView rvCurrencyItem;
    private ProgressBar pbCrypto;
    private List<Country> countries;
    private List<Country> clickedCountries;
    private StartFragmentListener listener;
    private CryptoCardAdapter cryptoCardAdapter;
    private CurrencyItemAdapter currencyItemAdapter;
    private LinearLayout llExpandCurrency;
    private static final int SPAN_COUNT  = 3;
    private RealmUtils realmUtils;

    //looks like you have no country cards yet. Click on the country pills above to add one.
    //No internet connection. Connect to the internet and pull to refresh

    public CryptoConvertFragment() {
        countries = new ArrayList<>();
        clickedCountries = new ArrayList<>();
    }

    public static CryptoConvertFragment newInstance(StartFragmentListener listener,
                                                    List<Country> countryList,
                                                    RealmUtils realmUtils) {

        Bundle args = new Bundle();

        CryptoConvertFragment fragment = new CryptoConvertFragment();
        fragment.listener = listener;
        fragment.countries = countryList;
        fragment.realmUtils = realmUtils;
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (StartFragmentListener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View cryptoConvertView =  inflater.inflate(R.layout.fragment_crypto_convert, container, false);
        setupViews(cryptoConvertView);
        return cryptoConvertView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (countries != null)
        bindUI(countries);
    }

    private void setupViews(View view) {
        pbCrypto = view.findViewById(R.id.pb_crypto);
        rvCryptoCard = view.findViewById(R.id.rv_crypto_card);
        rvCurrencyItem = view.findViewById(R.id.rv_currency);
        llExpandCurrency = view.findViewById(R.id.ll_expand_currency);

        LinearLayoutManager verticalLayoutManager = new LinearLayoutManager(this.getContext(),
                LinearLayoutManager.VERTICAL, false);

        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(this.getContext(),
                LinearLayoutManager.HORIZONTAL, false);

        rvCryptoCard.setLayoutManager(verticalLayoutManager);
        rvCurrencyItem.setLayoutManager(horizontalLayoutManager);

    }

    private void setClickedCountriesData(List<Country> countries) {
        for (Country country: countries){
            if (country.isChecked()) clickedCountries.add(country);
        }
    }

    private void setAdapter(List<Country> countries) {
        currencyItemAdapter = new CurrencyItemAdapter(this.requireContext(),
                this, realmUtils);
        cryptoCardAdapter = new CryptoCardAdapter(this.requireContext(), this);

        setClickedCountriesData(countries);
        currencyItemAdapter.setData(countries);
        cryptoCardAdapter.setData(clickedCountries);

        rvCryptoCard.setAdapter(cryptoCardAdapter);
        rvCurrencyItem.setAdapter(currencyItemAdapter);
    }

    private void bindUI(List<Country> countries) {
        if (countries == null || countries.size() == 0){
            return;
        }
        pbCrypto.setVisibility(View.GONE);
        setAdapter(countries);
        showItemDialog();

    }


    private void showItemDialog() {
        LayoutInflater inflater = LayoutInflater.from(this.requireContext());

        View dialogCurrencyView = inflater.inflate(R.layout.dialog_item, null);

        RecyclerView rvDialogItem = dialogCurrencyView.findViewById(R.id.rv_dialog_item);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.requireContext(), SPAN_COUNT);
        rvDialogItem.setLayoutManager(gridLayoutManager);
        rvDialogItem.setAdapter(currencyItemAdapter);

        //rvDialogItem.addOnItemTouchListener(new RecyclerTouchListener(this.requireContext(), this));

        AlertDialog dialog = new AlertDialog.Builder(this.requireContext())
                .setTitle("Select Currency")
                .setView(dialogCurrencyView)
                .setNegativeButton("Cancel", (dialog12, which) -> {
                    dialog12.cancel();
                })
                .create();
        llExpandCurrency.setOnClickListener(v -> {
            dialog.show();
        });
    }

    @Override
    public void onOpenCryptoFragment(Country country) {
        listener.onStartFragment(country);
    }

    @Override
    public void onItemClicked(Country country, boolean isChecked) {
        if (!country.isChecked()){
            clickedCountries.add(country);
        }else {
            clickedCountries.remove(country);
        }

        cryptoCardAdapter.notifyDataSetChanged();
        currencyItemAdapter.notifyDataSetChanged();

    }



    //mRealmUtils.getRealm().executeTransaction((realm -> {
    //                    currencyCountry.setChecked(true);
    //                    realm.insertOrUpdate(currencyCountry);
    //                }));
//    @Override
//    public void onClickItem(View view, int position) {
//        if (countries != null) {
//            Country currentCountry = countries.get(position);
//            if (!currentCountry.isChecked()) {
//            }
//            else {
//                realmUtils.getRealm().executeTransaction((realm -> {
//                currentCountry.setChecked(false);
//                realm.insertOrUpdate(currentCountry);
//            }));
//            }
//        }
//    }

    public interface StartFragmentListener{
        void onStartFragment(Country country);
        void onRefreshCountries();
    }
}
