package com.accolite.newsapplication.network.callback;

import android.accounts.NetworkErrorException;

public interface RemoteServiceCallback<T> {

    void onSuccess(T responseObject, int requestCode);

    void onError(Throwable throwable, int requestCode);

}
