package com.example.rummenigged.archandroidguidelinetest.domain.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by rummenigged on 20/03/18.
 */

public class Resource<T> {
    @NonNull private final int status;
    @NonNull private final T data;
    @NonNull private final String message;

    public static final int SUCCESS = 0;
    public static final int ERROR = 1;
    public static final int LOADING = 2;

    public Resource(@NonNull int status, @Nullable T data, @Nullable String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> Resource<T> sucess(@NonNull T data){
        return new Resource<>(SUCCESS, data, null);
    }

    public static <T> Resource<T> error(String msg, @NonNull T data){
        return new Resource<>(ERROR, data, msg);
    }

    public static <T> Resource<T> loading(@NonNull T data){
        return new Resource<>(LOADING, data, null);
    }

    public int getStatus() {
        return status;
    }

    public T getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }
}
