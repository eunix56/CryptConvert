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
import com.example.eunice.cryptconvert.internal.utils.AmountTextWatcher;
import com.example.eunice.cryptconvert.ui.adapter.CurrencySpinnerAdapter;

import java.util.ArrayList;
import java.util.List;

import static com.example.eunice.cryptconvert.internal.utils.StringUtils.formatValue;


/**
 * A simple {@link Fragment} subclass.
 */
public class CryptoCardFragment extends Fragment {
    private Country cryptoCountry;
    private EditText etCurrencyValue;
    private AppCompatSpinner spCurrency;
    private ImageView ivBackHome;
    private Button btConvert;
    private TextView firstConvertCurrency;
    private TextView tvFirstCurrency;
    private TextView secondConvertCurrency;
    private TextView tvSecondCurrency;
    private TextView tvOneCurrency;
    private TextView tvOneBtc;
    private TextView tvOneEth;
    private List<String> currencyItems;
    private String selectedItem = "";
    private LinearLayout llOneCurrency;
    private FrameLayout flLowScreen;
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
        View cryptoView = inflater.inflate(R.layout.fragment_crypto_card, container, false);

        ivBackHome = findView(cryptoView, R.id.iv_back_home);
        spCurrency = findView(cryptoView, R.id.sp_currency);
        etCurrencyValue = findView(cryptoView, R.id.et_currency_value);
        btConvert = findView(cryptoView, R.id.bt_convert);
        firstConvertCurrency = findView(cryptoView, R.id.first_convert_currency);
        tvFirstCurrency = findView(cryptoView, R.id.tv_first_currency);
        secondConvertCurrency = findView(cryptoView, R.id.second_convert_currency);
        tvSecondCurrency = findView(cryptoView, R.id.tv_second_currency);
        tvOneCurrency = findView(cryptoView, R.id.tv_one_currency);
        tvOneBtc = findView(cryptoView, R.id.tv_one_btc);
        tvOneEth = findView(cryptoView, R.id.tv_one_eth);
        llOneCurrency = findView(cryptoView, R.id.ll_one_currency);
        flLowScreen = findView(cryptoView, R.id.fl_low_screen);

        return cryptoView;
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

    private <T extends View> T findView(View fragmentView, @IdRes int fragmentId) {
        return fragmentView.findViewById(fragmentId);
    }

    private void bindUI() {
        ivBackHome.setOnClickListener(v ->requireFragmentManager().popBackStack());
        setCommasInValue();
        setupSpinner();
        btConvert.setOnClickListener(v -> {
            onClickConvert();
            InputMethodManager inputManager = (InputMethodManager) v.getContext()
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            if (inputManager != null)
                inputManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
        });

    }

    private void setCommasInValue() {
        setOnClick();
        etCurrencyValue.addTextChangedListener(new AmountTextWatcher(etCurrencyValue));

        etCurrencyValue.setOnEditorActionListener((v, actionId, event) -> {
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
            tvOneCurrency.setText(String.format("1 %s", cryptoCountry.getName()));
            tvOneBtc.setText(String.format("%s %s", formatValue(1/btc), BTC_TEXT));
            tvOneEth.setText(String.format("%s %s", formatValue(1/eth), ETH_TEXT));
        }else if (selectedItem.equals(BTC_TEXT)){
            tvOneCurrency.setText(String.format("1 %s", BTC_TEXT));
            tvOneBtc.setText(String.format("%s %s", formatValue(btc), cryptoCountry.getName()));
            tvOneEth.setText(String.format("%s %s", formatValue(btc/eth), ETH_TEXT));
        }else if (selectedItem.equals(ETH_TEXT)){
            tvOneCurrency.setText(String.format("1 %s", ETH_TEXT));
            tvOneBtc.setText(String.format("%s %s", formatValue(eth), cryptoCountry.getName()));
            tvOneEth.setText(String.format("%s %s", formatValue(eth/btc), BTC_TEXT));
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
        llOneCurrency.setOnClickListener(v -> {

        });

        flLowScreen.setOnClickListener(v -> {

        });
    }

    private void setupSpinner() {
        CurrencySpinnerAdapter adapter = new CurrencySpinnerAdapter(this.getContext(), getCurrencyItems(),
                R.layout.currency_name);

        spCurrency.setAdapter(adapter);
        spCurrency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedItem = adapter.getItem(position).toString();
                setupCurrencyTextViews(cryptoCountry.getBtcValue(), cryptoCountry.getEthValue());
                etCurrencyValue.setText("");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void onClickConvert() {
        if (etCurrencyValue == null || etCurrencyValue.getText() == null)
            return;

        if (selectedItem == null)
            return;

        double currencyValue = Double.parseDouble(AmountTextWatcher
                .trimCommas(etCurrencyValue.getText().toString()));
        double btcValue = cryptoCountry.getBtcValue();
        double ethValue = cryptoCountry.getEthValue();

        if (selectedItem.equals(cryptoCountry.getName())) {
            calculateCountryCurrencyValue(btcValue, ethValue, currencyValue);
            tvFirstCurrency.setText(BTC_TEXT);
            tvSecondCurrency.setText(ETH_TEXT);
        }else if (selectedItem.equals(BTC_TEXT)){
            calculateBTCCurrencyValue(btcValue, ethValue, currencyValue);
            tvFirstCurrency.setText(cryptoCountry.getName());
            tvSecondCurrency.setText(ETH_TEXT);
        }else if (selectedItem.equals(ETH_TEXT)){
            calculateETHCurrencyValue(btcValue, ethValue, currencyValue);
            tvFirstCurrency.setText(cryptoCountry.getName());
            tvSecondCurrency.setText(BTC_TEXT);
        }

    }

    private void calculateCountryCurrencyValue(double btcValue,
                                               double ethValue, double currencyValue) {
        Double btcCurrencyValue = currencyValue * (1/btcValue);
        firstConvertCurrency.setText(formatValue(btcCurrencyValue));
        //1.0725263787862862E-4

        Double ethCurrencyValue = currencyValue * (1/ethValue);
        secondConvertCurrency.setText(formatValue(ethCurrencyValue));
    }

    private void calculateBTCCurrencyValue(double btcValue, double ethValue, double currencyValue) {
        Double countryCurrencyValue = currencyValue * btcValue;
        firstConvertCurrency.setText(formatValue(countryCurrencyValue));

        Double ethCurrencyValue = currencyValue * (btcValue/ethValue);
        secondConvertCurrency.setText(formatValue(ethCurrencyValue));
    }

    private void calculateETHCurrencyValue(double btcValue, double ethValue, double currencyValue) {
        Double countryCurrencyValue =  currencyValue * ethValue;
        firstConvertCurrency.setText(formatValue(countryCurrencyValue));

        Double btcCurrencyValue = currencyValue * (ethValue/btcValue);
        secondConvertCurrency.setText(formatValue(btcCurrencyValue));
    }

}
