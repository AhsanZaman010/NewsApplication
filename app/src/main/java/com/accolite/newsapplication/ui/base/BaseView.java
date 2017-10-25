package com.accolite.newsapplication.ui.base;

/**
 * Created by Accolite- on 7/21/2017.
 */

public interface BaseView {

    void showLoading();

    void hideLoading();

    void showError(String status);

    boolean isNetworkAvailable();
}
