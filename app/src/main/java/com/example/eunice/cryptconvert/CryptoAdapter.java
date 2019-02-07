package com.example.eunice.cryptconvert;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.eunice.cryptconvert.MODEL.BTC;
import com.example.eunice.cryptconvert.MODEL.CryptoClass;
import com.example.eunice.cryptconvert.MODEL.ETH;

import java.util.ArrayList;

/**
 * Created by Eunice on 1/2/2019.
 */

    public class CryptoAdapter extends RecyclerView.Adapter<CryptoAdapter.CryptoViewHolder> {
    private BTC btcCurrency;
    private ETH ethCurrency;
    private Country country;
    private LayoutInflater inflater;
    private OnItemClickListener mOnItemClickListener;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;



    class CryptoViewHolder extends RecyclerView.ViewHolder{
        private TextView btcValue;
        private TextView Country;
        private TextView ethValue;
        private int position;


        public CryptoViewHolder(View itemView) {
            super(itemView);

            btcValue = (TextView)itemView.findViewById(R.id.btcValue);
            ethValue = (TextView)itemView.findViewById(R.id.ethValue);
            Country = (TextView)itemView.findViewById(R.id.country);
        }
    }

    public interface OnItemClickListener{
         void onItemClick(View v, int position);
    }

    public CryptoAdapter(Context context, BTC cryptoClass, ETH crypto, Country mCountry, OnItemClickListener onItemClickListener){
        inflater = LayoutInflater.from(context);
        this.country = mCountry;
        this.btcCurrency = cryptoClass;
        this.ethCurrency = crypto;
        country.setCountry();
        btcCurrency.addArray();
        ethCurrency.addArray();
        this.mOnItemClickListener = onItemClickListener;
    }

    @Override
    public CryptoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_item, parent, false);
        CryptoViewHolder viewHolder = new CryptoViewHolder(view);
        return viewHolder;
    }
    

    @Override
    public void onBindViewHolder(final CryptoViewHolder holder, int position) {
        holder.btcValue.setText(String.valueOf(btcCurrency.setArray(position)));
        holder.ethValue.setText(String.valueOf(ethCurrency.setArray(position)));
        holder.Country.setText(country.getCountry(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(v, holder.getAdapterPosition());
            }
        });
    }


    @Override
    public int getItemCount() {
        return country.size();
    }
}

