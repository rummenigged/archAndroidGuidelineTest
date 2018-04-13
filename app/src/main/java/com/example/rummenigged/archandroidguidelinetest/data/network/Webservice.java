package com.example.rummenigged.archandroidguidelinetest.data.network;

import com.example.rummenigged.archandroidguidelinetest.data.model.CryptocurrencyRaw;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by rummenigged on 23/03/18.
 */

public interface Webservice {
    @GET("ticker/")
    Call<List<CryptocurrencyRaw>> getCryptocurrencies();
}
