package com.example.eunice.cryptconvert.ui.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.eunice.cryptconvert.R;

import java.util.List;


public class CurrencySpinnerAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<String> mCurrencySpinnerItems;
    @LayoutRes private int mResourceId;

    public CurrencySpinnerAdapter(Context context, List<String> currencySpinnerItems, @LayoutRes int resourceId) {
        inflater = LayoutInflater.from(context);
        mCurrencySpinnerItems = currencySpinnerItems;
        mResourceId = resourceId;
    }

    @Override
    public int getCount() {
        return mCurrencySpinnerItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mCurrencySpinnerItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getItemView(position, parent);
    }

    private View getItemView(int position, ViewGroup parent) {
        View itemView = inflater.inflate(mResourceId, parent, false);
        CurrencyItemHolder viewHolder = new CurrencyItemHolder(itemView);

        String currencyText = mCurrencySpinnerItems.get(position);

        viewHolder.tvCurrencyText.setText(currencyText);

        return itemView;
    }

    private class CurrencyItemHolder{
        private TextView tvCurrencyText;

        CurrencyItemHolder(View itemView)  {
            tvCurrencyText = itemView.findViewById(R.id.tv_currency_text);
        }

    }
}
