package com.accolite.newsapplication.ui.base;

import android.app.Application;

import com.accolite.newsapplication.di.component.DaggerDepComponent;
import com.accolite.newsapplication.di.component.DepComponent;
import com.accolite.newsapplication.di.module.AppModule;
import com.accolite.newsapplication.di.module.NetworkModule;

import java.io.File;

public class NewsApplication extends Application {

    DepComponent deps;



    @Override
    public void onCreate() {
        super.onCreate();
        File cacheFile = new File(getCacheDir(), "responses");
        /*RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this)
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);*/
        deps = DaggerDepComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule(cacheFile, this))
                .build();

    }

    public DepComponent getDeps() {
        return deps;
    }

    //Used for testing (providing mocks)
    public void setDepComponent(DepComponent depComponent) {
        this.deps = depComponent;
    }

}
