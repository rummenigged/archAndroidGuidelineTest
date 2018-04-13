package com.example.rummenigged.archandroidguidelinetest.data.service;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.example.rummenigged.archandroidguidelinetest.R;
import com.example.rummenigged.archandroidguidelinetest.domain.model.Cryptocurrency;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rummenigged on 20/03/18.
 */

public class MockService {

    public LiveData<Cryptocurrency> getCryptocurrency() throws JSONException {
        MutableLiveData<Cryptocurrency> ldCryptocurrency = new MutableLiveData<>();
        Cryptocurrency cryptocurrency = new Cryptocurrency();
        cryptocurrency.setName("Android Coin");
        cryptocurrency.setValue(100.52);

        ldCryptocurrency.setValue(cryptocurrency);
        return ldCryptocurrency;
    }

    private List<Cryptocurrency> handlerCryptocurrencyList(JSONArray list) throws JSONException {
        JSONArray array = list;
        List<Cryptocurrency> cryptocurrencies = new ArrayList<>();
        for (int i = 0 ; i < array.length() ; i++) {
            Cryptocurrency cryptocurrency = handlerCryptocurrencyOnject((JSONObject) array.get(i));
            cryptocurrencies.add(cryptocurrency);
        }

        return cryptocurrencies;
    }

    private Cryptocurrency handlerCryptocurrencyOnject(JSONObject o) throws JSONException {
        Cryptocurrency response = new Cryptocurrency();
        response.setName(o.getString("name"));
        response.setValue(o.getDouble("value"));
        return response;
    }
}
