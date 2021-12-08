package com.example.eunice.cryptconvert.ui;


import android.content.Context;
import android.os.Bundle;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.appcompat.widget.AppCompatSpinner;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.eunice.cryptconvert.R;
import com.example.eunice.cryptconvert.data.db.Country;
import com.example.eunice.cryptconvert.databinding.FragmentCryptoCardBinding;
import com.example.eunice.cryptconvert.internal.utils.AmountTextWatcher;
import com.example.eunice.cryptconvert.ui.adapter.CurrencySpinnerAdapter;

import java.util.ArrayList;
import java.util.List;

import static com.example.eunice.cryptconvert.internal.utils.StringUtils.formatValue;


/**
 * A simple {@link Fragment} subclass.
 */
public class CryptoCardFragment extends Fragment {
    private FragmentCryptoCardBinding cryptoCardBinding;
    private Country cryptoCountry;
    private List<String> currencyItems;
    private String selectedItem = "";
    private final static String BTC_TEXT = "BTC";
    private final static String ETH_TEXT = "ETH";

    public static CryptoCardFragment newInstance(Country country) {
        Bundle args = new Bundle();
        CryptoCardFragment fragment = new CryptoCardFragment();
        fragment.cryptoCountry = country;
        fragment.setArguments(args);
        return fragment;
    }


    public CryptoCardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        cryptoCardBinding = FragmentCryptoCardBinding.inflate(inflater, container, false);

        return cryptoCardBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bindUI();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private void bindUI() {
        cryptoCardBinding.ivBackHome.setOnClickListener(v ->requireFragmentManager().popBackStack());
        setCommasInValue();
        setupSpinner();
        cryptoCardBinding.btConvert.setOnClickListener(v -> {
            onClickConvert();
            InputMethodManager inputManager = (InputMethodManager) v.getContext()
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            if (inputManager != null)
                inputManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
        });

    }

    private void setCommasInValue() {
        setOnClick();
        cryptoCardBinding.etCurrencyValue.addTextChangedListener(new AmountTextWatcher(
                cryptoCardBinding.etCurrencyValue));

        cryptoCardBinding.etCurrencyValue.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE){
                onClickConvert();
                InputMethodManager inputManager = (InputMethodManager) v.getContext()
                        .getSystemService(Context.INPUT_METHOD_SERVICE);
                if (inputManager != null)
                    inputManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
                return true;
            }

            return true;
        });
    }

    private void setupCurrencyTextViews(double btc, double eth) {
        if (selectedItem == null)
            return;

        if (selectedItem.equals(cryptoCountry.getName())) {
            cryptoCardBinding.tvOneCurrency.setText(String.format("1 %s", cryptoCountry.getName()));
            cryptoCardBinding.tvOneBtc.setText(String.format("%s %s", formatValue(1/btc), BTC_TEXT));
            cryptoCardBinding.tvOneEth.setText(String.format("%s %s", formatValue(1/eth), ETH_TEXT));
        }else if (selectedItem.equals(BTC_TEXT)){
            cryptoCardBinding.tvOneCurrency.setText(String.format("1 %s", BTC_TEXT));
            cryptoCardBinding.tvOneBtc.setText(String.format("%s %s", formatValue(btc), cryptoCountry.getName()));
            cryptoCardBinding.tvOneEth.setText(String.format("%s %s", formatValue(btc/eth), ETH_TEXT));
        }else if (selectedItem.equals(ETH_TEXT)){
            cryptoCardBinding.tvOneCurrency.setText(String.format("1 %s", ETH_TEXT));
            cryptoCardBinding.tvOneBtc.setText(String.format("%s %s", formatValue(eth), cryptoCountry.getName()));
            cryptoCardBinding.tvOneEth.setText(String.format("%s %s", formatValue(eth/btc), BTC_TEXT));
        }
    }

    private List<String> getCurrencyItems() {
        currencyItems = new ArrayList<>();
        currencyItems.add(cryptoCountry.getName());
        currencyItems.add(BTC_TEXT);
        currencyItems.add(ETH_TEXT);

        return currencyItems;
    }

    private void setOnClick() {
        cryptoCardBinding.llOneCurrency.setOnClickListener(v -> {

        });

        cryptoCardBinding.flLowScreen.setOnClickListener(v -> {

        });
    }

    private void setupSpinner() {
        CurrencySpinnerAdapter adapter = new CurrencySpinnerAdapter(this.getContext(), getCurrencyItems(),
                R.layout.currency_name);

        cryptoCardBinding.spCurrency.setAdapter(adapter);
        cryptoCardBinding.spCurrency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedItem = adapter.getItem(position).toString();
                setupCurrencyTextViews(cryptoCountry.getBtcValue(), cryptoCountry.getEthValue());
                cryptoCardBinding.etCurrencyValue.setText("");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void onClickConvert() {
        if (cryptoCardBinding.etCurrencyValue.getText() == null
        || cryptoCardBinding.etCurrencyValue.getText().toString().isEmpty())
            return;

        if (selectedItem == null)
            return;

        double currencyValue = Double.parseDouble(AmountTextWatcher
                .trimCommas(cryptoCardBinding.etCurrencyValue.getText().toString()));
        double btcValue = cryptoCountry.getBtcValue();
        double ethValue = cryptoCountry.getEthValue();

        if (selectedItem.equals(cryptoCountry.getName())) {
            calculateCountryCurrencyValue(btcValue, ethValue, currencyValue);
            cryptoCardBinding.tvFirstCurrency.setText(BTC_TEXT);
            cryptoCardBinding.tvSecondCurrency.setText(ETH_TEXT);
        }else if (selectedItem.equals(BTC_TEXT)){
            calculateBTCCurrencyValue(btcValue, ethValue, currencyValue);
            cryptoCardBinding.tvFirstCurrency.setText(cryptoCountry.getName());
            cryptoCardBinding.tvSecondCurrency.setText(ETH_TEXT);
        }else if (selectedItem.equals(ETH_TEXT)){
            calculateETHCurrencyValue(btcValue, ethValue, currencyValue);
            cryptoCardBinding.tvFirstCurrency.setText(cryptoCountry.getName());
            cryptoCardBinding.tvSecondCurrency.setText(BTC_TEXT);
        }

    }

    private void calculateCountryCurrencyValue(double btcValue,
                                               double ethValue, double currencyValue) {
        Double btcCurrencyValue = currencyValue * (1/btcValue);
        cryptoCardBinding.firstConvertCurrency.setText(formatValue(btcCurrencyValue));
        //1.0725263787862862E-4

        Double ethCurrencyValue = currencyValue * (1/ethValue);
        cryptoCardBinding.secondConvertCurrency.setText(formatValue(ethCurrencyValue));
    }

    private void calculateBTCCurrencyValue(double btcValue, double ethValue, double currencyValue) {
        Double countryCurrencyValue = currencyValue * btcValue;
        cryptoCardBinding.firstConvertCurrency.setText(formatValue(countryCurrencyValue));

        Double ethCurrencyValue = currencyValue * (btcValue/ethValue);
        cryptoCardBinding.secondConvertCurrency.setText(formatValue(ethCurrencyValue));
    }

    private void calculateETHCurrencyValue(double btcValue, double ethValue, double currencyValue) {
        Double countryCurrencyValue =  currencyValue * ethValue;
        cryptoCardBinding.firstConvertCurrency.setText(formatValue(countryCurrencyValue));

        Double btcCurrencyValue = currencyValue * (ethValue/btcValue);
        cryptoCardBinding.secondConvertCurrency.setText(formatValue(btcCurrencyValue));
    }

}
