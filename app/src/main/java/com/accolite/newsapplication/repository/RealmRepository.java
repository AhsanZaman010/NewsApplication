package com.accolite.newsapplication.repository;

import android.app.Application;

import com.accolite.newsapplication.model.news.NewsResponse;


public class RealmRepository implements ILocalRepository {

    private static final String ID = "id";
    private final Application mApplication;

    public RealmRepository(Application application){
        this.mApplication = application;
    }

    @Override
    public NewsResponse getNews(String query) {
        return null;
    }
}
