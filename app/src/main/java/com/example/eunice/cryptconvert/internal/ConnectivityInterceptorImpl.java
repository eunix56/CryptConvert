package com.example.eunice.cryptconvert.internal;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.io.IOException;
import java.net.ContentHandler;

import okhttp3.Response;

import static android.content.Context.CONNECTIVITY_SERVICE;

public class ConnectivityInterceptorImpl implements ConnectivityInterceptor {
    private Context mContext;

    public ConnectivityInterceptorImpl(Context context) {
        mContext = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        if (!isOnline())
            throw new NoInternetConnectivityException();
        return chain.proceed(chain.request());
    }

    private boolean isOnline() {
        ConnectivityManager manager = (ConnectivityManager) mContext.getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        return info != null && info.isConnected();
    }
}
