package com.example.rezwann.cities.model;

import android.content.Context;
import android.util.Log;

import com.example.rezwann.cities.app.App;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * Created by RezwanN on 24-01-2018.
 */

public class LocalRepositoryImpl implements LocalRepository {


    @Inject
    public CitiesDao citiesDAO;
    @Inject
    public Executor executor;

    public LocalRepositoryImpl(Context context) {
        ((App)context).getAppComponent().inject(this);
        /*this.citiesDAO = citiesDAO;
        executor = exec;*/
    }

    @Override
    public Flowable<List<Cities>> getCities() {
        Log.v("getCities===" , "end");
            return citiesDAO.getCities();
    }

    @Override
    public void insertCities(List<Cities> cities) {
        for(Cities city : cities){
            citiesDAO.insert(city);
        }
    }
}
