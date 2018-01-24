package com.example.rezwann.cities.dagger;

import com.example.rezwann.cities.model.Cities;
import com.example.rezwann.cities.network.CitiesAPI;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Modifier;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.rezwann.cities.app.Constants.BASE_URL;

@Module
public class NetworkModule {

    @Singleton
    @Provides
    Retrofit provideRetrofit(){
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                        .serializeNulls()
                        .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
                        .create()))
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .build();
    }
//new GsonBuilder() .excludeFieldsWithoutExposeAnnotation() .create()))
    //GsonConverterFactory.create()
    @Singleton
    @Provides
    CitiesAPI provideUsersService(Retrofit retrofit){
        return retrofit.create(CitiesAPI.class);
    }

}
