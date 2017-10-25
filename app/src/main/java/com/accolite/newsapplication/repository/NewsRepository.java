package com.accolite.newsapplication.repository;

import android.support.annotation.NonNull;


import com.accolite.newsapplication.model.news.NewsResponse;
import com.accolite.newsapplication.network.callback.RemoteServiceCallback;
import com.accolite.newsapplication.network.service.IRemoteNewsService;

import io.reactivex.disposables.Disposable;

public class NewsRepository implements IRepository {

    private static final String ID = "id";
    private final IRemoteNewsService mRemoteNewsService;
    private final ILocalRepository mLocalRepository;

    public NewsRepository(IRemoteNewsService IRemoteNewsService, ILocalRepository localRepository){
        mRemoteNewsService = IRemoteNewsService;
        mLocalRepository = localRepository;
    }

    @Override
    public Disposable getNews(@NonNull final RemoteServiceCallback callback, int requestCode, String query, boolean forceUpdate) {
        Disposable disposable = null;
        if(forceUpdate){
            disposable = mRemoteNewsService.getNews(callback, requestCode, query);
        } else {
            NewsResponse newsResponse = mLocalRepository.getNews(query);
            if(null != newsResponse){
                callback.onSuccess(newsResponse, requestCode);
            } else {
                disposable = mRemoteNewsService.getNews(callback, requestCode, query);
            }
        }
        return disposable;
    }

    @Override
    public ILocalRepository getLocalRepository() {
        return mLocalRepository;
    }

}