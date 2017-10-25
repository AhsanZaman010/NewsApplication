package com.accolite.newsapplication.di.module;

import com.accolite.newsapplication.ui.module.news.presenter.NewsPresenter;
import com.accolite.newsapplication.ui.module.news.view.NewsActivity;
import com.accolite.newsapplication.ui.module.news.view.NewsView;

import dagger.Module;
import dagger.Provides;

@Module
public class NewsModule {


    private final NewsView mNewsView;

    public NewsModule(NewsActivity newsView) {
        mNewsView = newsView;
    }

    @Provides
    NewsView providesView() {
        return (NewsView) mNewsView;
    }

    /*@Provides
    NewsPresenter providesP(NewsView newsView) {
        return new NewsPresenter(newsView);
    }*/
}
