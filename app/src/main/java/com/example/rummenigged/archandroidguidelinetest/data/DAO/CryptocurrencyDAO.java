package com.example.rummenigged.archandroidguidelinetest.data.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.rummenigged.archandroidguidelinetest.data.model.CryptocurrencyRaw;

import java.util.List;

/**
 * Created by rummenigged on 26/03/18.
 */

@Dao
public interface CryptocurrencyDAO {

    @Query("SELECT * FROM cryptocurrency")
    List<CryptocurrencyRaw> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long[] insertAll(List<CryptocurrencyRaw> cryptocurrencyRawList);
}
