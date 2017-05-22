package edu.udacity.mou.bakingapp;


import android.app.Application;

import edu.udacity.mou.bakingapp.dagger.components.BakingComponent;
import edu.udacity.mou.bakingapp.dagger.components.DaggerBakingComponent;
import edu.udacity.mou.bakingapp.dagger.modules.AppModule;
import edu.udacity.mou.bakingapp.dagger.modules.NetworkModule;

/**
 * Created by mou on 22/05/17.
 */

public class BakingApp extends Application {
    private BakingComponent bakingComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        bakingComponent = DaggerBakingComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule())
                .build();

    }

    public BakingComponent getBakingComponent() {
        return bakingComponent;
    }
}
