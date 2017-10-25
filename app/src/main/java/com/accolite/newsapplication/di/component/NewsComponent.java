package com.accolite.newsapplication.di.component;

import android.app.Activity;

import com.accolite.newsapplication.di.module.NewsModule;
import com.accolite.newsapplication.ui.module.news.view.NewsActivity;
import com.accolite.newsapplication.ui.module.news.view.NewsView;

import dagger.Subcomponent;

@Subcomponent(
        modules = {NewsModule.class }
)
public interface NewsComponent {

        void inject(NewsActivity newsActivity);

}
