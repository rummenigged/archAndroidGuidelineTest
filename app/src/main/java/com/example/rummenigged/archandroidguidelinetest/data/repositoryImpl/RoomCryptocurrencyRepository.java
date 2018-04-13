package com.example.rummenigged.archandroidguidelinetest.data.repositoryImpl;


import android.annotation.SuppressLint;
import android.os.AsyncTask;

import com.example.rummenigged.archandroidguidelinetest.data.AppDatabase;
import com.example.rummenigged.archandroidguidelinetest.data.model.CryptocurrencyRaw;

import com.example.rummenigged.archandroidguidelinetest.domain.repository.LocalCryptocurrencyRepository;

import java.util.List;

/**
 * Created by rummenigged on 23/03/18.
 */

public class RoomCryptocurrencyRepository implements LocalCryptocurrencyRepository {

    private AppDatabase database;

    public RoomCryptocurrencyRepository(AppDatabase database){
        this.database = database;
    }

    @SuppressLint("StaticFieldLeak")
    @Override
    public void getCryptocurrency(Callback<List<CryptocurrencyRaw>> callback) {
        new AsyncTask<Void, Void, List<CryptocurrencyRaw>>(){

            @Override
            protected List<CryptocurrencyRaw> doInBackground(Void... voids) {
                return database.cryptocurrencyDAO().getAll();
            }

            @Override
            protected void onPostExecute(List<CryptocurrencyRaw> cryptocurrencyRaws) {
                super.onPostExecute(cryptocurrencyRaws);
                callback.onCompleted(cryptocurrencyRaws);
            }
        }.execute();

    }

    @Override
    public void saveCryptocurrency(List<CryptocurrencyRaw> list, Callback<Long[]> callback) {
        callback.onCompleted(database.cryptocurrencyDAO().insertAll(list));
    }

}
