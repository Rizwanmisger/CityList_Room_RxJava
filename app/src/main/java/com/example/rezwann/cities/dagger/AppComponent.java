package com.example.rezwann.cities.dagger;



import com.example.rezwann.cities.MainActivity;
import com.example.rezwann.cities.model.LocalRepositoryImpl;
import com.example.rezwann.cities.viewModel.CitiesViewModel;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by 10620225 on 12/20/2017.
 */
@Singleton
@Component(modules = {AppModule.class, NetworkModule.class, ViewModelModule.class, DBModule.class})
public interface AppComponent {
    void inject(CitiesViewModel target);
    void inject(MainActivity target);
    void inject(LocalRepositoryImpl target);
}
