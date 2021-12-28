package com.example.eunice.cryptconvert.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.TextView;

import com.example.eunice.cryptconvert.R;
import com.example.eunice.cryptconvert.data.db.Country;
import com.example.eunice.cryptconvert.internal.utils.Constants;
import com.example.eunice.cryptconvert.internal.utils.StringUtils;

import java.util.List;

public class CryptoCardAdapter extends RecyclerView.Adapter<CryptoCardAdapter.CardViewHolder>{

    private LayoutInflater inflater;
    private List<Country> mCountryList;
    private OpenFragmentListener mListener;

    public CryptoCardAdapter(Context context, OpenFragmentListener listener) {
        inflater = LayoutInflater.from(context);
        mListener = listener;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View cryptoCardView = inflater.inflate(R.layout.crypto_card_item, viewGroup, false);
        return new CardViewHolder(cryptoCardView);
    }


    @Override
    public void onBindViewHolder(@NonNull CardViewHolder viewHolder, int i) {
        Country countryValue = mCountryList.get(i);
        viewHolder.setData(countryValue);
        viewHolder.itemView.setOnClickListener(v -> {
            if (mListener != null){
                mListener.onOpenCryptoFragment(countryValue);
            }
        });
    }

    public void setData(List<Country> countries) {
        this.mCountryList = countries;
        //notifyDataSetChanged();
        notifyItemRangeInserted(Constants.ITEM_POSITION_START, countries.size());
    }

    public void addItem(Country country) {
        if (country == null) return;

        if (mCountryList.contains(country)) return;

        mCountryList.add(country);
        int position = mCountryList.indexOf(country);

        notifyItemInserted(position);
    }

    public void removeItem(Country country) {
        if (country == null) return;

        if (!mCountryList.contains(country)) return;

        int position = mCountryList.indexOf(country);

        mCountryList.remove(country);
        notifyItemRemoved(position);
    }

    static class CardViewHolder extends RecyclerView.ViewHolder{
        private TextView tvCountryCode;
        private TextView tvBTCValue;
        private TextView tvETHValue;

        CardViewHolder(@NonNull View itemView) {
            super(itemView);

            tvCountryCode = itemView.findViewById(R.id.tv_country_code);
            tvBTCValue = itemView.findViewById(R.id.tv_btc_value);
            tvETHValue = itemView.findViewById(R.id.tv_eth_value);

        }

        @SuppressLint("SetTextI18n")
        private void setData(Country country){
            this.tvCountryCode.setText(country.getName());
            this.tvBTCValue.setText(StringUtils.formatValueWithComma(country.getBtcValue()));
            this.tvETHValue.setText(StringUtils.formatValueWithComma(country.getEthValue()));

        }



    }

    @Override
    public int getItemCount() {
        return mCountryList.size();
    }

    public interface OpenFragmentListener{
        void onOpenCryptoFragment(Country country);
    }
}
