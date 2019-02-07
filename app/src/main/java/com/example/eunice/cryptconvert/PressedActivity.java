package com.example.eunice.cryptconvert;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.eunice.cryptconvert.MODEL.BTC;
import com.example.eunice.cryptconvert.MODEL.CryptoClass;
import com.example.eunice.cryptconvert.MODEL.ETH;

import org.w3c.dom.Text;

import java.text.DecimalFormat;

import static com.example.eunice.cryptconvert.FrontActivity.EXTRA_ETH;
import static com.example.eunice.cryptconvert.FrontActivity.btc;
import static com.example.eunice.cryptconvert.FrontActivity.eth;

public class PressedActivity extends AppCompatActivity {
    private EditText countryCurrency;
    private DecimalFormat df;
    private TextView countryName;
    private TextView btcCurrency;
    private TextView ethCurrency;
    private View view;
    private Button currencyButton;
    private LinearLayout btcLinear;
    private LinearLayout ethLinear;
    private int position;
    String input;
    public static final String EXTRA_NAME = "name";
    public static final String EXTRA_ETH = "eth";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pressed);

        countryCurrency = (EditText)findViewById(R.id.countryCurrency);
        countryName = (TextView)findViewById(R.id.countryName);
        btcCurrency = (TextView)findViewById(R.id.btcCurrencyValue);
        ethCurrency = (TextView)findViewById(R.id.ethCurrencyValue);
        currencyButton = (Button)findViewById(R.id.currencyButton);
        btcLinear = (LinearLayout)findViewById(R.id.btcLinear);
        ethLinear = (LinearLayout)findViewById(R.id.ethLinear);
        view = findViewById(R.id.view);

        df = new DecimalFormat("0.000");
        Intent i = getIntent();
        if (i.hasExtra(EXTRA_ETH)) {
            position = i.getIntExtra(EXTRA_ETH, 0);
            Country country = new Country();
            country.setCountry();
            countryName.setText(country.getCountry(position));

            countryCurrency.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (s.length() != 0){
                        currencyButton.setBackgroundColor(getResources()
                        .getColor(R.color.button));

                        currencyButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                btc.addArray();
                                eth.addArray();
                                double btcValue = Double.parseDouble(countryCurrency.getText().toString())/btc.setArray(position);
                                double ethValue = Double.parseDouble(countryCurrency.getText().toString())/eth.setArray(position);

                                view.setVisibility(View.VISIBLE);
                                btcCurrency.setText(String.valueOf(df.format(btcValue)));
                                btcLinear.setVisibility(View.VISIBLE);
                                ethCurrency.setText(String.valueOf(df.format(ethValue)));
                                ethLinear.setVisibility(View.VISIBLE);
                            }
                        });
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });


//                currencyButton.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        btc.addArray();
//                        eth.addArray();
//                        double btcValue = btc.setArray(position)* Double.parseDouble(countryCurrency.getText().toString());
//                        double ethValue = eth.setArray(position)* Double.parseDouble(countryCurrency.getText().toString());
//
//                        btcCurrency.setText(String.valueOf(btcValue));
//                        btcLinear.setVisibility(View.VISIBLE);
//                        ethCurrency.setText(String.valueOf(ethValue));
//                        ethLinear.setVisibility(View.VISIBLE);
//                    }
//                });
        }


        }


    }


