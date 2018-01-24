package com.example.rezwann.cities.dagger;

import android.app.Application;
import android.content.Context;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class AppModule {
    private Application application;

    public AppModule(Application application){
        this.application = application;
    }

    @Singleton
    @Provides
    Context provideApplication(){
        return this.application;
    }

    @Singleton
    @Provides
    public Executor getExecutor(){
        return  Executors.newFixedThreadPool(2);
    }
}
