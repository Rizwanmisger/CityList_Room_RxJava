package com.example.rezwann.cities.viewModel;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.rezwann.cities.app.App;
import com.example.rezwann.cities.model.Cities;
import com.example.rezwann.cities.model.CitiesDao;
import com.example.rezwann.cities.model.LocalRepository;
import com.example.rezwann.cities.model.Result;
import com.example.rezwann.cities.network.CitiesAPI;

import java.util.ArrayList;
import java.util.List;


import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

import static com.example.rezwann.cities.app.Constants.CITY_URL;

/**
 * Created by RezwanN on 22-01-2018.
 */

public class CitiesViewModel extends java.util.Observable {
    final static String TAG = CitiesViewModel.class.getName();
    @Inject
    CitiesAPI apiSource;
    @Inject
    LocalRepository localRepository;
    @Inject
    CitiesDao dao;

    List<Cities> citiesList;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public List<Cities> getCitiesList() {
        return citiesList;
    }

    public CitiesViewModel(Context context) {
        ((App) context).getAppComponent().inject(this);
        citiesList = new ArrayList<>();
        //   getCitiesFromLocalRepo();
        getCitiesFromService();
    }

    void updateCitiesList(List<Cities> list) {
        citiesList.addAll(list);
        Log.v(TAG, "UpdatedList " + list.size());
        setChanged();
        notifyObservers();
    }

    public void getCitiesFromService() {
        Disposable disposable = apiSource.fetchCities(CITY_URL)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .subscribe(new Consumer<Result>() {
                               @Override
                               public void accept(Result result) throws Exception {
                                //   localRepository.insertCities(result.getObjects());
                                   updateCitiesList(result.getObjects());
                               }
                           },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Log.v(TAG, "Eror in API " + throwable.getLocalizedMessage());
                            }
                        }
                );
        compositeDisposable.add(disposable);
    }

    public void getCitiesFromLocalRepo() {
        localRepository.getCities()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Cities>>() {
                    @Override
                    public void accept(List<Cities> citiesList) throws Exception {
                        if(citiesList != null) {
                            Log.v("Repo" , "Succes in repo" );
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("Repo", "exception " + throwable.getMessage());
                    }
                });

    }


}

