package com.example.rezwann.cities.model;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by RezwanN on 24-01-2018.
 */
@Database(entities = {Cities.class}, version = 5, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase{

    public abstract CitiesDao citiesDao();
}
