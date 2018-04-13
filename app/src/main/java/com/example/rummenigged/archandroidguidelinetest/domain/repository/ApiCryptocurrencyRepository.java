package com.example.rummenigged.archandroidguidelinetest.domain.repository;

import android.arch.lifecycle.LiveData;

import com.example.rummenigged.archandroidguidelinetest.data.model.CryptocurrencyRaw;
import com.example.rummenigged.archandroidguidelinetest.domain.model.Resource;

import java.util.List;

/**
 * Created by rummenigged on 20/03/18.
 */

public interface ApiCryptocurrencyRepository {
    LiveData<Resource<List<CryptocurrencyRaw>>> getCryptocurrency(boolean forceWebserviceCall);
}
