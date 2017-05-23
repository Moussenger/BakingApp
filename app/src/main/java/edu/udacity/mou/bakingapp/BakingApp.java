package edu.udacity.mou.bakingapp;


import android.app.Application;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import edu.udacity.mou.bakingapp.bus.managers.ApiManager;
import edu.udacity.mou.bakingapp.dagger.components.BakingComponent;
import edu.udacity.mou.bakingapp.dagger.components.DaggerBakingComponent;
import edu.udacity.mou.bakingapp.dagger.modules.AppModule;
import edu.udacity.mou.bakingapp.dagger.modules.ManagerModule;
import edu.udacity.mou.bakingapp.dagger.modules.NetworkModule;
import edu.udacity.mou.bakingapp.dagger.modules.ServiceModule;
import edu.udacity.mou.bakingapp.dagger.modules.StorageModule;
import timber.log.Timber;

/**
 * Created by mou on 22/05/17.
 */

public class BakingApp extends Application {
    @Inject EventBus bus;
    @Inject ApiManager apiManager;

    private BakingComponent bakingComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        createBakingComponent();
        inject();
        addBusManagers(apiManager);
        configTimber();
    }

    public BakingComponent getBakingComponent() {
        return bakingComponent;
    }

    private void inject() {
        getBakingComponent().inject(this);
    }

    private void createBakingComponent() {
        bakingComponent = DaggerBakingComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule())
                .serviceModule(new ServiceModule())
                .managerModule(new ManagerModule())
                .storageModule(new StorageModule())
                .build();
    }

    private void configTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    private void addBusManagers(Object... managers) {
        for(Object manager : managers) {
            bus.register(manager);
        }
    }
}
