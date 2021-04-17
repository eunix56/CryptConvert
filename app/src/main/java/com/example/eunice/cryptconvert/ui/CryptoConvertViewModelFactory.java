package com.example.eunice.cryptconvert.ui;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.eunice.cryptconvert.data.repository.CryptocurrencyRepository;
import com.example.eunice.cryptconvert.ui.CryptoConvertViewModel;

public class CryptoConvertViewModelFactory extends ViewModelProvider.AndroidViewModelFactory {
    private CryptocurrencyRepository mRepository;
    private Application application;
    /**
     * Creates a {@code AndroidViewModelFactory}
     *
     * @param application an application to pass in {@link AndroidViewModel}
     *
     */
    public CryptoConvertViewModelFactory(@NonNull Application application, CryptocurrencyRepository repository) {
        super(application);
        this.application = application;
        mRepository = repository;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(CryptoConvertViewModel.class))
            return (T) new CryptoConvertViewModel(application, mRepository);
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
