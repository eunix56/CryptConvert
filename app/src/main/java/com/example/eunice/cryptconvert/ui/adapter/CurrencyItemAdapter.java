package com.example.eunice.cryptconvert.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.eunice.cryptconvert.R;
import com.example.eunice.cryptconvert.data.db.Country;
import com.example.eunice.cryptconvert.data.utils.RealmUtils;

import java.util.List;

public class CurrencyItemAdapter extends RecyclerView.Adapter<CurrencyItemAdapter.CurrencyItemHolder>{
    private LayoutInflater inflater;
    private List<Country> mCountryList;
    private OnItemClickedListener listener;
    private RealmUtils mRealmUtils;

    public CurrencyItemAdapter(Context context,
                               OnItemClickedListener itemClickListener, RealmUtils realmUtils) {
        inflater = LayoutInflater.from(context);
        listener = itemClickListener;
        mRealmUtils = realmUtils;
    }

    @NonNull
    @Override
    public CurrencyItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View currencyItemView = inflater.inflate(R.layout.currency_item, viewGroup, false);
        return new CurrencyItemHolder(currencyItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CurrencyItemHolder currencyItemHolder, int i) {
        Country currencyCountry = mCountryList.get(i);
        currencyItemHolder.setData(currencyCountry);
    }

    public void setData(List<Country> countries) {
        this.mCountryList = countries;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mCountryList.size();
    }

    class CurrencyItemHolder extends RecyclerView.ViewHolder {
        private TextView tvCurrencyName;
        private LinearLayout llCurrency;

        CurrencyItemHolder(@NonNull View itemView) {
            super(itemView);
            tvCurrencyName = itemView.findViewById(R.id.tv_currency_name);
            llCurrency = itemView.findViewById(R.id.ll_curency);
        }

        public void setData(Country country) {
            tvCurrencyName.setText(country.getName());
            if (!country.isChecked()) {
                tvCurrencyName.setBackgroundResource(R.drawable.bg_currency_item);
            }else {
                tvCurrencyName.setBackgroundResource(R.drawable.bg_currency_clicked_item);
            }
            llCurrency.setOnClickListener(v -> onClick(country));
        }

        void onClick(Country currencyCountry) {
            if (!currencyCountry.isChecked()) {
                tvCurrencyName.setBackgroundResource(R.drawable.bg_currency_clicked_item);
                listener.onItemClicked(currencyCountry, currencyCountry.isChecked());
                mRealmUtils.getRealm().executeTransaction((realm -> {
                    currencyCountry.setChecked(true);
                    realm.insertOrUpdate(currencyCountry);
                }));
            } else {
                tvCurrencyName.setBackgroundResource(R.drawable.bg_currency_item);
                listener.onItemClicked(currencyCountry, currencyCountry.isChecked());
                mRealmUtils.getRealm().executeTransaction((realm -> {
                    currencyCountry.setChecked(false);
                    realm.insertOrUpdate(currencyCountry);
                }));
            }
        }
    }

    public interface OnItemClickedListener {
        void onItemClicked(Country country, boolean isChecked);
    }

}
