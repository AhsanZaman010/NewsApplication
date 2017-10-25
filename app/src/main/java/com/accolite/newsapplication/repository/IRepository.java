package com.accolite.newsapplication.repository;

import android.support.annotation.NonNull;

import com.accolite.newsapplication.network.callback.RemoteServiceCallback;

import io.reactivex.disposables.Disposable;

public interface IRepository {

    Disposable getNews(@NonNull RemoteServiceCallback callback, int requestCode, String query, boolean forceUpdate);

    ILocalRepository getLocalRepository();

}
