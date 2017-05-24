package edu.udacity.mou.bakingapp.dagger.modules;

import android.app.Application;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import edu.udacity.mou.bakingapp.ui.activitys.main.MainContract;
import edu.udacity.mou.bakingapp.ui.activitys.main.MainPresenter;
import edu.udacity.mou.bakingapp.ui.fragments.recipes.RecipesContract;
import edu.udacity.mou.bakingapp.ui.fragments.recipes.RecipesPresenter;

/**
 * Created by mou on 22/05/17.
 */

@Module
public class MVPModule {

    @Provides
    @Singleton
    MainContract.Presenter provideMainPresenter(Application application, EventBus bus) {
        return new MainPresenter(application.getApplicationContext(), bus);
    }

    @Provides
    @Singleton
    RecipesContract.Presenter provideRecipesPresenter(Application application, EventBus bus) {
        return new RecipesPresenter(application.getApplicationContext(), bus);
    }
}
