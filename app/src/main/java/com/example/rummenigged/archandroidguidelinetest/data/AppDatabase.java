package com.example.rummenigged.archandroidguidelinetest.data;

import android.arch.persistence.room.RoomDatabase;

import com.example.rummenigged.archandroidguidelinetest.data.DAO.CryptocurrencyDAO;
import com.example.rummenigged.archandroidguidelinetest.data.model.CryptocurrencyRaw;

/**
 * Created by rummenigged on 26/03/18.
 */

@android.arch.persistence.room.Database(entities = {CryptocurrencyRaw.class}, version = 3, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase{
    public abstract CryptocurrencyDAO cryptocurrencyDAO();
}
