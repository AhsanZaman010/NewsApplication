package com.accolite.newsapplication.di.module;

import android.annotation.SuppressLint;
import android.app.Application;
import android.support.annotation.NonNull;

import com.accolite.newsapplication.BuildConfig;
import com.accolite.newsapplication.network.service.IRemoteNewsService;
import com.accolite.newsapplication.network.service.NetworkService;
import com.accolite.newsapplication.network.service.RemoteNewsService;
import com.accolite.newsapplication.repository.ILocalRepository;
import com.accolite.newsapplication.repository.IRepository;
import com.accolite.newsapplication.repository.NewsRepository;
import com.accolite.newsapplication.repository.RealmRepository;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

@Module
public class NetworkModule {
    private final Application mApplication;
    private File cacheFile;

    public NetworkModule(File cacheFile, Application application) {
        this.cacheFile = cacheFile;
        mApplication = application;
    }

    @Provides
    @Singleton
    Retrofit provideCall() {
        Cache cache = null;
        try {
            cache = new Cache(cacheFile, 10 * 1024 * 1024);
        } catch (Exception e) {
            e.printStackTrace();
        }

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cache(cache)
                .connectTimeout(BuildConfig.LIMIT, TimeUnit.SECONDS)
                .build();


        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASEURL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    @SuppressWarnings("unused")
    public NetworkService providesNetworkService(
            Retrofit retrofit) {
        return retrofit.create(NetworkService.class);
    }

    @Provides
    @Singleton
    @SuppressWarnings("unused")
    public IRemoteNewsService providesService(
            NetworkService networkService) {
        return new RemoteNewsService(networkService);
    }

    @Provides
    @Singleton
    IRepository providesRepository(IRemoteNewsService contactsService, ILocalRepository localRepository) {
        return new NewsRepository(contactsService, localRepository);
    }

    @Provides
    @Singleton
    ILocalRepository providesLocalRepository(Application application) {
        return new RealmRepository(application);
    }
}