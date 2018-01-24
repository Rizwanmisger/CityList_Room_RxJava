package com.example.rezwann.cities.model;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;

/**
 * Created by RezwanN on 24-01-2018.
 */

public interface LocalRepository {
    public Flowable<List<Cities>> getCities();
    public void insertCities(List<Cities> cities);
}
