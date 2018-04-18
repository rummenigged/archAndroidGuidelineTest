package com.example.rummenigged.archandroidguidelinetest.data.repositoryImpl;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.rummenigged.archandroidguidelinetest.data.model.CryptocurrencyRaw;
import com.example.rummenigged.archandroidguidelinetest.data.network.Webservice;
import com.example.rummenigged.archandroidguidelinetest.data.util.DataMapper;
import com.example.rummenigged.archandroidguidelinetest.data.util.NetworkBoundResource;
import com.example.rummenigged.archandroidguidelinetest.domain.model.Resource;
import com.example.rummenigged.archandroidguidelinetest.domain.repository.ApiCryptocurrencyRepository;
import com.example.rummenigged.archandroidguidelinetest.domain.repository.LocalCryptocurrencyRepository;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by rummenigged on 23/03/18.
 */

public class RetrofitApiCryptocurrencyRepository implements ApiCryptocurrencyRepository {


    private Webservice webservice;
    private LocalCryptocurrencyRepository localRepository;
    private DataMapper mapper;
    private static final String TAG = RetrofitApiCryptocurrencyRepository.class.getSimpleName();

    public RetrofitApiCryptocurrencyRepository(LocalCryptocurrencyRepository localRepository, Webservice webservice, DataMapper mapper){
        this.localRepository = localRepository;
        this.mapper = mapper;
        this.webservice = webservice;
    }


    @Override
    public LiveData getCryptocurrency(boolean forceWebserviceCall) {

        return new NetworkBoundResource<List<CryptocurrencyRaw>, List<CryptocurrencyRaw>>() {

//            @Override
//            protected DataMapper map() {
//                return mapper;
//            }

            @Override
            protected void saveCallResult(@NonNull List<CryptocurrencyRaw> item) {
                localRepository.saveCryptocurrency(item, new LocalCryptocurrencyRepository.Callback<Long[]>() {
                    @Override
                    public void onCompleted(Long[] longs) {

                    }

                    @Override
                    public void onError(Throwable t) {

                    }
                });
            }

            @Override
            protected boolean shouldFetch(@Nullable List<CryptocurrencyRaw> data) {
                return data == null || data.isEmpty() || forceWebserviceCall;
//                return true;
            }

            @SuppressLint("StaticFieldLeak")
            @NonNull
            @Override
            protected LiveData<List<CryptocurrencyRaw>> loadFromDb() {
                Log.d(TAG, "Entrei loadFromDb: ");
                final MutableLiveData<List<CryptocurrencyRaw>> data = new MutableLiveData<>();
//                new AsyncTask<Void, Void, List<CryptocurrencyRaw>>() {
//                    @Override
//                    protected List<CryptocurrencyRaw> doInBackground(Void... voids) {
                        List<CryptocurrencyRaw> response = new ArrayList<>();
                        localRepository.getCryptocurrency(new LocalCryptocurrencyRepository.Callback<List<CryptocurrencyRaw>>() {
                            @Override
                            public void onCompleted(List<CryptocurrencyRaw> cryptocurrencyRaws) {
//                                response.addAll(cryptocurrencyRaws);
                                Log.d(TAG, "Retornei dados do banco de dados:");
                                data.setValue(cryptocurrencyRaws);
                            }

                            @Override
                            public void onError(Throwable t) {

                            }
                        });

//                        return response;
//                    }

//                    @Override
//                    protected void onPostExecute(List<CryptocurrencyRaw> cryptocurrencyRawList) {
//                        data.setValue(cryptocurrencyRawList);
//                    }
//
//                }.execute();

//                data.setValue(response);
                Log.d(TAG, "Retornei dados para o network bound resource:");
                return data;
            }

            @NonNull
            @Override
            protected LiveData<Resource<List<CryptocurrencyRaw>>> createCall() {
                Log.d(TAG, "createCall: ");
                MutableLiveData<Resource<List<CryptocurrencyRaw>>> ldResponse = new MutableLiveData<>();
                webservice.getCryptocurrencies().enqueue(new Callback<List<CryptocurrencyRaw>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<CryptocurrencyRaw>> call, @NonNull Response<List<CryptocurrencyRaw>> response) {
                        if (response.body() != null && response.code() == 200){
                            Resource<List<CryptocurrencyRaw>> resource = Resource.sucess(response.body());
                            ldResponse.setValue(resource);
                        }else{
                            Resource<List<CryptocurrencyRaw>> resource = Resource.error(response.message(),response.body());
                            ldResponse.setValue(resource);
                        }
                    }
                    @Override
                    public void onFailure(@NonNull Call<List<CryptocurrencyRaw>> call, @NonNull Throwable t) {
                        Log.d(TAG, "onFailure: " + t.getMessage());
                    }
                });
                return ldResponse;
            }

        }.map(mapper)
        .getAsLiveData();
    }
}
