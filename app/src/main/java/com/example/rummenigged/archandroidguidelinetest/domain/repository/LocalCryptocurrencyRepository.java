package com.example.rummenigged.archandroidguidelinetest.domain.repository;

import com.example.rummenigged.archandroidguidelinetest.data.model.CryptocurrencyRaw;


import java.util.List;

/**
 * Created by rummenigged on 23/03/18.
 */

public interface LocalCryptocurrencyRepository {

    interface Callback<ReturnType>{
        void onCompleted(ReturnType returnType);
        void onError(Throwable t);
    }

    void getCryptocurrency(Callback<List<CryptocurrencyRaw>> callback);

    void saveCryptocurrency(List<CryptocurrencyRaw> list, Callback<Long[]> callback);
}
