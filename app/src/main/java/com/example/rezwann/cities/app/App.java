package com.example.rezwann.cities.app;

import android.app.Application;


import com.example.rezwann.cities.dagger.AppComponent;
import com.example.rezwann.cities.dagger.AppModule;
import com.example.rezwann.cities.dagger.DaggerAppComponent;

/**
 * Created by RezwanN on 22-01-2018.
 */

public class App extends Application {
    AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = initDagger(this);
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    private AppComponent initDagger(App application){
            return DaggerAppComponent.builder()
                    .appModule(new AppModule((application)))
                    .build();
    }

}
