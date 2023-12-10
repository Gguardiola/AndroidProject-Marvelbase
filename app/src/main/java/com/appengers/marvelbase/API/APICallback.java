package com.appengers.marvelbase.API;

public interface APICallback<T> {
    void onSuccess(T data);
    void onError(Throwable t);
}
