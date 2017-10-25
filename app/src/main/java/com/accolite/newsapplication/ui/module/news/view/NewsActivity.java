package com.accolite.newsapplication.ui.module.news.view;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.accolite.newsapplication.R;
import com.accolite.newsapplication.di.module.NewsModule;
import com.accolite.newsapplication.ui.base.BaseActivity;
import com.accolite.newsapplication.ui.base.BasePresenter;
import com.accolite.newsapplication.ui.base.NewsApplication;
import com.accolite.newsapplication.ui.module.news.presenter.NewsPresenter;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class NewsActivity extends BaseActivity implements NewsView {

    @Inject
    NewsPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        ButterKnife.bind(this);
        ((NewsApplication) getApplication()).getDeps().plus(new NewsModule(this)).inject(this);
        mPresenter.getNews("football", true);
    }

    @Override
    protected BasePresenter getPresenter() {
        return mPresenter;
    }

    @Override
    protected void retry() {

    }

    @Override
    protected void onCancelErrorDialog(DialogInterface dialog) {

    }
}
