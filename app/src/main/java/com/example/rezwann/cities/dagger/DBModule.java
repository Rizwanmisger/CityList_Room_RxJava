package com.example.rezwann.cities.dagger;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.util.Log;

import com.example.rezwann.cities.model.AppDatabase;
import com.example.rezwann.cities.model.CitiesDao;
import com.example.rezwann.cities.model.LocalRepository;
import com.example.rezwann.cities.model.LocalRepositoryImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by RezwanN on 23-01-2018.
 */
@Module
public class DBModule {


    @Singleton
    @Provides
    public AppDatabase getAppDatabase(Context application){
        return Room.databaseBuilder(application.getApplicationContext(), AppDatabase.class, "citiesdb")
                .build();
    }
    @Singleton
    @Provides
    public CitiesDao getCitiesDao(AppDatabase db){
        return db.citiesDao();
    }
    @Singleton
    @Provides
    public LocalRepository getLocalRepository(Context context){
        return new LocalRepositoryImpl(context);
    }
   /* public LocalRepository getLocalRepository(CitiesDao citiesDAO, Executor exec){
        return new LocalRepositoryImpl(citiesDAO, exec);
    }*/

}
