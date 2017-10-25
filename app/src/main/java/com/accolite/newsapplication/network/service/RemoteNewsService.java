package com.accolite.newsapplication.network.service;

import android.accounts.NetworkErrorException;

import com.accolite.newsapplication.model.news.NewsResponse;
import com.accolite.newsapplication.network.callback.RemoteServiceCallback;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RemoteNewsService implements IRemoteNewsService {
    private final NetworkService networkService;

    public RemoteNewsService(NetworkService networkService) {
        this.networkService = networkService;
    }

    @Override
    public Disposable getNews(final RemoteServiceCallback callback, final int requestCode, String query) {
        return networkService.getNews(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        callback.onError(new NetworkErrorException(throwable), requestCode);
                    }
                })
                .subscribe(new Consumer<NewsResponse>() {
                    @Override
                    public void accept(NewsResponse newsResponse) throws Exception {
                        callback.onSuccess(newsResponse, requestCode);
                    }
                });
    }
}
