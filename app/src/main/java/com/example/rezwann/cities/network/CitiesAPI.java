package com.example.rezwann.cities.network;


import com.example.rezwann.cities.model.Result;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;


public interface CitiesAPI {

    @GET
    Flowable<Result> fetchCities(@Url String url);
}
