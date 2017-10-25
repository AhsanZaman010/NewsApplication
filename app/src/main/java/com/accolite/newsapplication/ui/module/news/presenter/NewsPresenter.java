package com.accolite.newsapplication.ui.module.news.presenter;

import android.accounts.NetworkErrorException;
import android.text.TextUtils;

import com.accolite.newsapplication.model.news.Hit;
import com.accolite.newsapplication.model.news.NewsResponse;
import com.accolite.newsapplication.repository.IRepository;
import com.accolite.newsapplication.ui.base.BasePresenter;
import com.accolite.newsapplication.ui.module.news.view.NewsView;
import com.accolite.newsapplication.utils.CollectionUtils;
import com.accolite.newsapplication.utils.NetworkConstants;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class NewsPresenter extends BasePresenter {


    private static final int NEWS_REQUEST_CODE = 101;
    private final NewsView mView;
    private final IRepository mRepository;
    private ObservableEmitter<CharSequence> mSearchEmitter;

    @Inject
    public NewsPresenter(NewsView view, IRepository repository) {
        super(view);
        mView = view;
        mRepository = repository;
    }

    public void getNews(String query, boolean forceUpdate){
        if(mView.isNetworkAvailable()) {
            mView.showLoading();
            mView.hideContent();
            Disposable disposable = mRepository.getNews(this, NEWS_REQUEST_CODE, query, forceUpdate);
            mCompositeDisposable.add(disposable);
        } else {
            mView.hideContent();
            mView.showError(NetworkConstants.NETWORK_UNAVAILABLE);
        }
    }

    @Override
    public void onSuccess(Object responseObject, int requestCode) {
        if(null == responseObject){
            mView.hideLoading();
            mView.showNoDataFound();
        } else if(responseObject instanceof NewsResponse){
            NewsResponse newsResponse = ((NewsResponse) responseObject);
            if(TextUtils.isEmpty(newsResponse.getError())) {
                mView.hideLoading();
                mView.showContent();
                mView.updateNews(newsResponse.getHits());
                if (CollectionUtils.isEmpty(newsResponse.getHits())) {
                    mView.showNoDataFound();
                }
            } else {
                onError(new NetworkErrorException(newsResponse.getError()), requestCode);
            }
        } else {
            mView.hideContent();
            onError(new NetworkErrorException(NetworkConstants.SERVICE_UNAVAILABLE), NEWS_REQUEST_CODE);
        }
    }

    public void onItemClick(Hit item) {
        if(item != null){
            mView.openWebPage(item);
        }
    }

    public void onTextChanged(CharSequence s) {
        if(null != mSearchEmitter){
            mSearchEmitter.onNext(s);
        }
    }



    private void setUpSearchQuery(){
        Disposable disposable = Observable
                .create(new ObservableOnSubscribe<CharSequence>() {
                    @Override
                    public void subscribe(final ObservableEmitter<CharSequence> emitter) throws Exception {
                        mSearchEmitter = emitter;

                    }
                })
                .observeOn(Schedulers.io())
                .debounce(1, TimeUnit.SECONDS).map(new Function<CharSequence, String>() {
                    @Override
                    public String apply(CharSequence charSequence) throws Exception {
                        return charSequence.toString();
                    }
                })
                .throttleLast(500, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {
                        return s;
                    }
                })
                .observeOn(Schedulers.io())
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(String s) throws Exception {
                        if(!TextUtils.isEmpty(s) && s.length()>=2) {
                            return true;
                        }
                        return false;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.hideLoading();
                        mView.showError(NetworkConstants.GENERIC_ERROR);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        getNews(s, true);
                    }
                })
                .subscribe();
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void onStart() {
        super.onStart();
        setUpSearchQuery();
    }
}
