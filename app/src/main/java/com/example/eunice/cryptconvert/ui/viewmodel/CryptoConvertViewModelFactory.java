package com.example.eunice.cryptconvert.ui.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;

import com.example.eunice.cryptconvert.data.repository.CryptocurrencyRepository;

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
