package com.example.rummenigged.archandroidguidelinetest.data.util;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.os.AsyncTask;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.util.Log;

import com.example.rummenigged.archandroidguidelinetest.domain.model.Resource;

import java.util.List;

/**
 * Created by rummenigged on 20/03/18.
 */


public abstract class NetworkBoundResource<ResultType, RequestType> {
    private static final  String TAG = NetworkBoundResource.class.getSimpleName();
    private final MediatorLiveData<Resource<ResultType>> result = new MediatorLiveData<>();

    @MainThread
    protected NetworkBoundResource(){

        result.setValue(Resource.loading(null));

        LiveData<ResultType> dbSource = loadFromDb();
        result.addSource(dbSource, data -> {
            result.removeSource(dbSource);

            if (shouldFetch(data)){
                Log.d(TAG, "NetworkBoundResource: shouldFetch");
                fetchFromNetwork(dbSource);
            }else{
                result.addSource(dbSource, newData ->
                   result.setValue(Resource.sucess(newData)));
            }
        });
    }

    private void fetchFromNetwork(final LiveData<ResultType> dbSource){
        LiveData<Resource<RequestType>> networkResponse = createCall();

        result.addSource(dbSource, newData ->
            result.setValue(Resource.loading(newData)));

        result.addSource(networkResponse, response -> {
            result.removeSource(networkResponse);
            result.removeSource(dbSource);
            if (response.getStatus() == Resource.SUCCESS){
                if (isDataSafe(response.getData()).getData()){
                    saveResultAndReinit(response);
                }else{
                    onFetchFailed();
                    result.addSource(dbSource, newData ->
                            result.setValue(Resource.error("Error", newData)));
                }
            }else{
                onFetchFailed();
                result.addSource(dbSource, newData ->
                    result.setValue(Resource.error("Error", newData)));
            }
        });
    }

    @SuppressLint("StaticFieldLeak")
    private void saveResultAndReinit(Resource<RequestType> response){
        Log.d("NetworkBoundResource", "saveResultAndReinit: ");
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                saveCallResult(response.getData());
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                result.addSource(loadFromDb(),
                        newData -> result.setValue(Resource.sucess(newData)));
            }
        }.execute();
    }

    private Resource<Boolean> isDataSafe(RequestType data){
        try {
            if (data instanceof List) {
                for (int i = 0; i < ((List) data).size(); i++) {
                    map().assertEssentialParams(((List) data).get(i));
                }
            } else {
                map().assertEssentialParams(data);
            }
        }catch (EssentialParamMissingException e){
            return Resource.error(e.getMessage(), false);
        }

        return Resource.sucess(true);
    }

    public final LiveData<Resource<ResultType>> getAsLiveData(){
        return result;
    }

    @WorkerThread
    protected abstract DataMapper map();

    @WorkerThread
    protected abstract void saveCallResult(@NonNull RequestType item);

    @MainThread
    protected abstract boolean shouldFetch(@Nullable ResultType data);

    @NonNull @MainThread
    protected abstract LiveData<ResultType> loadFromDb();

    @NonNull
    @MainThread
    protected abstract LiveData<Resource<RequestType>> createCall();

    @MainThread
    protected void onFetchFailed() {
    }
}
