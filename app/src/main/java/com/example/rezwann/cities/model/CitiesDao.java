package com.example.rezwann.cities.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;


import io.reactivex.Flowable;
import io.reactivex.Observable;

/**
 * Created by RezwanN on 24-01-2018.
 */
@Dao
public interface CitiesDao {

    @Query("SELECT * FROM cities")
    Flowable<List<Cities>> getCities();


    @Query("SELECT * FROM cities")
    List<Cities> getCity();


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Cities city);


}
