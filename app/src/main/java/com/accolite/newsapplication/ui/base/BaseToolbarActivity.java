package com.accolite.newsapplication.ui.base;

import android.support.v7.widget.Toolbar;

import com.accolite.newsapplication.R;

import butterknife.BindView;


public abstract class BaseToolbarActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    public void setToolbar(){
        setSupportActionBar(mToolbar);
    }
}
