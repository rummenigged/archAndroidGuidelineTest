package com.example.rummenigged.archandroidguidelinetest.view;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.rummenigged.archandroidguidelinetest.data.model.CryptocurrencyRaw;
import com.example.rummenigged.archandroidguidelinetest.data.repositoryImpl.NetworkRepository;
import com.example.rummenigged.archandroidguidelinetest.domain.model.Resource;
import com.example.rummenigged.archandroidguidelinetest.domain.repository.ApiCryptocurrencyRepository;

import java.util.List;

/**
 * Created by rummenigged on 20/03/18.
 */

public class MainViewModel extends ViewModel {
    private ApiCryptocurrencyRepository repository;
    private NetworkRepository networkRepository;
    private LiveData<Resource<List<CryptocurrencyRaw>>> ldCryptocurrency;
    private LiveData<Resource<Boolean>> ldIsConnected;

    MainViewModel(ApiCryptocurrencyRepository repository, NetworkRepository networkRepository){
        this.repository = repository;
        this.networkRepository = networkRepository;
    }

    public LiveData<Resource<List<CryptocurrencyRaw>>> getCryptocurrency(){
        return ldCryptocurrency = repository.getCryptocurrency(false);
    }

    public LiveData<Resource<List<CryptocurrencyRaw>>> refreshCryptocurrency(){
        return ldCryptocurrency = repository.getCryptocurrency(true);
    }

    public LiveData<Resource<Boolean>> isConnected(){
      return ldIsConnected = networkRepository.isConnected();
    }

    static class MainViewModelFactory implements ViewModelProvider.Factory{

        private ApiCryptocurrencyRepository repository;
        private NetworkRepository networkRepository;

        public MainViewModelFactory(ApiCryptocurrencyRepository repository, NetworkRepository networkRepository){
            this.repository = repository;
            this.networkRepository = networkRepository;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if(modelClass.isAssignableFrom(MainViewModel.class)){
                return (T) new MainViewModel(this.repository, this.networkRepository);
            }

            throw new IllegalArgumentException("Unknow View Model Class");
        }
    }
}
