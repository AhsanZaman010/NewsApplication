package com.accolite.newsapplication.ui.base;

public interface BaseView {

    void showLoading();

    void hideLoading();

    void showError(String status);

    boolean isNetworkAvailable();
}
