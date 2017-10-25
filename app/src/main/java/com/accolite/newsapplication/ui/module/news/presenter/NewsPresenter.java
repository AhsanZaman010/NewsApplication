package com.accolite.newsapplication.ui.module.news.presenter;

import android.accounts.NetworkErrorException;

import com.accolite.newsapplication.repository.IRepository;
import com.accolite.newsapplication.ui.base.BasePresenter;
import com.accolite.newsapplication.ui.base.BaseView;
import com.accolite.newsapplication.ui.module.news.view.NewsView;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

public class NewsPresenter extends BasePresenter {


    private static final int NEWS_REQUEST_CODE = 101;
    private final NewsView mView;
    private final IRepository mRepository;

    @Inject
    public NewsPresenter(NewsView view, IRepository repository) {
        super(view);
        mView = view;
        mRepository = repository;
    }

    public void getNews(String query, boolean forceUpdate){
        if(mView.isNetworkAvailable()) {
            Disposable disposable = mRepository.getNews(this, NEWS_REQUEST_CODE, query, forceUpdate);
            mCompositeDisposable.add(disposable);
        } else {
            mView.showError("No internet detected. Please check your connection and try again.");
        }
    }

    @Override
    public void onSuccess(Object responseObject, int requestCode) {
        super.onSuccess(responseObject, requestCode);

    }
}
