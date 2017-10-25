package com.accolite.newsapplication.network.service;


import com.accolite.newsapplication.network.callback.RemoteServiceCallback;

import io.reactivex.disposables.Disposable;


public interface IRemoteNewsService {

    Disposable getNews(RemoteServiceCallback callback, int requestCode, String query);
}
