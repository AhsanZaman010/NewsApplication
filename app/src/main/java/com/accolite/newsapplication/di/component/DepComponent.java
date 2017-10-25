package com.accolite.newsapplication.di.component;


import com.accolite.newsapplication.di.module.AppModule;
import com.accolite.newsapplication.di.module.NetworkModule;
import com.accolite.newsapplication.di.module.NewsModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})
public interface DepComponent {

    NewsComponent plus(NewsModule newsModule);
}