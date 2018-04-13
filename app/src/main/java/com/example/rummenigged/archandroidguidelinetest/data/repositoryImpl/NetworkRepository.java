package com.example.rummenigged.archandroidguidelinetest.data.repositoryImpl;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import com.example.rummenigged.archandroidguidelinetest.data.util.NetworkUtils;
import com.example.rummenigged.archandroidguidelinetest.domain.model.Resource;

/**
 * Created by rummenigged on 26/03/18.
 */

public class NetworkRepository {

    private Context context;

    public NetworkRepository(Context context){
        this.context = context;
    }
    public LiveData<Resource<Boolean>> isConnected(){
        return new NetworkUtils(context).getAsLiveData();
    }
}
