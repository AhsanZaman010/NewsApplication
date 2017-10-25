package com.accolite.newsapplication.ui.module.news.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.accolite.newsapplication.R;
import com.accolite.newsapplication.di.module.NewsModule;
import com.accolite.newsapplication.model.news.Hit;
import com.accolite.newsapplication.ui.base.BasePresenter;
import com.accolite.newsapplication.ui.base.BaseToolbarActivity;
import com.accolite.newsapplication.ui.base.NewsApplication;
import com.accolite.newsapplication.ui.module.news.adapter.NewsListAdapter;
import com.accolite.newsapplication.ui.module.news.presenter.NewsPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsActivity extends BaseToolbarActivity implements NewsView {

    @Inject
    NewsPresenter mPresenter;
    private NewsListAdapter mNewsAdapter;

    @BindView(R.id.news_rv)
    RecyclerView mRecyclerView;
    @BindView(R.id.search_et)
    EditText mSearchET;
    @BindView(R.id.no_data_tv)
    TextView mErrorTV;
    @BindView(R.id.progress)
    ProgressBar mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        ButterKnife.bind(this);
        ((NewsApplication) getApplication()).getDeps().plus(new NewsModule(this)).inject(this);
        setToolbar();
        setTitle(getString(R.string.news_title));
        mNewsAdapter = new NewsListAdapter(this, new ArrayList<Hit>(), new NewsListAdapter.OnItemClickListener() {
            @Override
            public void onClick(Hit item) {
                mPresenter.onItemClick(item);
            }
        });
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        DividerItemDecoration verticalDecoration = new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL);
        Drawable verticalDivider = ContextCompat.getDrawable(this, R.drawable.divider);
        verticalDecoration.setDrawable(verticalDivider);
        mRecyclerView.addItemDecoration(verticalDecoration);
        mRecyclerView.setAdapter(mNewsAdapter);
        //mPresenter.getNews("football", true);
        mSearchET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mPresenter.onTextChanged(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
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

    @Override
    public void updateNews(List<Hit> items){
        mNewsAdapter.updateItems(items).notifyDataSetChanged();
    }

    @Override
    public void showLoading() {
        mProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mProgress.setVisibility(View.GONE);
    }

    @Override
    public void showContent() {
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideContent() {
        mErrorTV.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public void showNoDataFound() {
        mErrorTV.setVisibility(View.VISIBLE);
        mErrorTV.setText(getString(R.string.no_news_found));
    }

    @Override
    public void openWebPage(Hit item) {
        if(item != null){
            Intent intent = new Intent(this, NewsWebActivity.class);
            intent.putExtra(getString(R.string.url), item.getUrl());
            startActivity(intent);
        }
    }

    @Override
    public void showError(String status) {
        mErrorTV.setVisibility(View.VISIBLE);
        mErrorTV.setText(status);
    }
}
