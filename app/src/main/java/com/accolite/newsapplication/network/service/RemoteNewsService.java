package com.accolite.newsapplication.network.service;

import com.accolite.newsapplication.model.news.NewsResponse;
import com.accolite.newsapplication.network.callback.RemoteServiceCallback;
import com.accolite.newsapplication.utils.NetworkConstants;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
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
                        callback.onError(throwable, requestCode);
                    }
                })
                .onErrorReturn(new Function<Throwable, NewsResponse>() {
                    @Override
                    public NewsResponse apply(Throwable throwable) throws Exception {
                        NewsResponse newsResponse = new NewsResponse();
                        if(throwable != null) {
                            newsResponse.setError(NetworkConstants.GENERIC_ERROR + " : " + throwable.getMessage());
                        } else {
                            newsResponse.setError(NetworkConstants.GENERIC_ERROR);
                        }
                        return newsResponse;
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
