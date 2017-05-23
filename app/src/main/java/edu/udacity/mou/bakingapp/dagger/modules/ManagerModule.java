package edu.udacity.mou.bakingapp.dagger.modules;

import android.app.Application;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import edu.udacity.mou.bakingapp.api.BakingApi;
import edu.udacity.mou.bakingapp.bus.managers.ApiManager;
import edu.udacity.mou.bakingapp.bus.managers.ApiManagerImpl;
import edu.udacity.mou.bakingapp.services.api.ApiService;
import edu.udacity.mou.bakingapp.services.api.ApiServiceImpl;
import edu.udacity.mou.bakingapp.services.api.callbacks.GetRecipesCallback;

/**
 * Created by mou on 22/05/17.
 */

@Module
public class ManagerModule {

    @Provides
    @Singleton
    ApiManager provideApiManager(ApiService apiService) {
        return new ApiManagerImpl(apiService);
    }
}
