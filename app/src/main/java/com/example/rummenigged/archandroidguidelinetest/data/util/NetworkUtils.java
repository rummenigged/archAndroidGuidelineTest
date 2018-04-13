package com.example.rummenigged.archandroidguidelinetest.data.util;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.example.rummenigged.archandroidguidelinetest.domain.model.Resource;

/**
 * Created by rummenigged on 26/03/18.
 */

public class NetworkUtils {
    MutableLiveData<Resource<Boolean>> resource = new MutableLiveData();

     public NetworkUtils(Context context){
        boolean isConnected;
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        isConnected = networkInfo != null && networkInfo.isConnectedOrConnecting();
         Log.d("NetworkUtils", "NetworkUtils: " + isConnected);
        if (isConnected){
            resource.setValue(Resource.sucess(isConnected));
        }else{
            resource.setValue(Resource.error("Você Não Possui Conexão com a Internet", isConnected));
        }

    }

    public LiveData<Resource<Boolean>> getAsLiveData(){
        return resource;
    }
}
