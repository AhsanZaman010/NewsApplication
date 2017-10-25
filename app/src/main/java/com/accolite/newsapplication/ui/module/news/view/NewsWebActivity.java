package com.accolite.newsapplication.ui.module.news.view;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.accolite.newsapplication.R;
import com.accolite.newsapplication.ui.base.BasePresenter;
import com.accolite.newsapplication.ui.base.BaseToolbarActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsWebActivity extends BaseToolbarActivity {

    @BindView(R.id.web_view)
    WebView mWebView;
    @BindView(R.id.progress)
    ProgressBar mProgress;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_web);
        ButterKnife.bind(this);
        setToolbar();
        mWebView.setWebViewClient(new CustomWebViewClient());
        mWebView.getSettings().setJavaScriptEnabled(true);
        String url = getIntent().getStringExtra(getString(R.string.url));
        mWebView.loadUrl(url);
    }

    @Override
    public void setToolbar() {
        super.setToolbar();
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void retry() {

    }

    @Override
    protected void onCancelErrorDialog(DialogInterface dialog) {

    }

    public class CustomWebViewClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            showLoading();
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            hideLoading();
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;

        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()){
            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item != null && item.getItemId() == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showLoading() {
        mProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mProgress.setVisibility(View.GONE);
    }
}