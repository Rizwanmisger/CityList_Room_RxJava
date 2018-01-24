package com.example.rezwann.cities.dagger;

import android.content.Context;

import com.example.rezwann.cities.network.CitiesAPI;
import com.example.rezwann.cities.viewModel.CitiesViewModel;

import dagger.Module;
import dagger.Provides;

/**
 * Created by RezwanN on 22-01-2018.
 */
@Module
public class ViewModelModule {
    CitiesAPI api;
    @Provides
    public CitiesViewModel getCitiesViewModel(Context context){
        return new CitiesViewModel(context);
    }
}
