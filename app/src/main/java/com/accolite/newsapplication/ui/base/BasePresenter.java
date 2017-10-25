package com.accolite.newsapplication.ui.base;

import android.accounts.NetworkErrorException;
import android.util.Log;


import com.accolite.newsapplication.network.callback.RemoteServiceCallback;

import io.reactivex.disposables.CompositeDisposable;


public class BasePresenter implements RemoteServiceCallback {

    private final BaseView mView;
    protected CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private final String TAG = getClass().getSimpleName();

    protected BasePresenter(BaseView view){
        mView = view;
    }

    public void onStart(){
        mCompositeDisposable = new CompositeDisposable();
    }

    public void onStop(){
        if(mCompositeDisposable!=null){
            mCompositeDisposable.dispose();
        }
    }

    @Override
    public void onSuccess(Object responseObject, int requestCode) {

    }

    @Override
    public void onError(Throwable throwable, int requestCode) {
        mView.hideLoading();
        if(throwable!=null){
            mView.showError(throwable.getMessage());
            Log.e(TAG, "Error received: "+ throwable.getMessage());
        } else {
            Log.e(TAG, "Error received");
            mView.showError("");
        }
    }
}
