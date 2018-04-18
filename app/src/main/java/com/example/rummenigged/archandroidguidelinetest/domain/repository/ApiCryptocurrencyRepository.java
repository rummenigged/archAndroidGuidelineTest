package com.example.rummenigged.archandroidguidelinetest.domain.repository;

import android.arch.lifecycle.LiveData;

/**
 * Created by rummenigged on 20/03/18.
 */

public interface ApiCryptocurrencyRepository {
    LiveData getCryptocurrency(boolean forceWebserviceCall);
}
