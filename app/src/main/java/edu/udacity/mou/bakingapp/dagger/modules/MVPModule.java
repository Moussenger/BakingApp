package edu.udacity.mou.bakingapp.dagger.modules;

import android.app.Application;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import edu.udacity.mou.bakingapp.model.Ingredient;
import edu.udacity.mou.bakingapp.storage.StorageModel;
import edu.udacity.mou.bakingapp.ui.activitys.main.MainContract;
import edu.udacity.mou.bakingapp.ui.activitys.main.MainPresenter;
import edu.udacity.mou.bakingapp.ui.activitys.steps.StepsContract;
import edu.udacity.mou.bakingapp.ui.activitys.steps.StepsPresenter;
import edu.udacity.mou.bakingapp.ui.fragments.recipes.RecipesContract;
import edu.udacity.mou.bakingapp.ui.fragments.recipes.RecipesPresenter;
import edu.udacity.mou.bakingapp.ui.fragments.step.StepContract;
import edu.udacity.mou.bakingapp.ui.fragments.step.StepPresenter;

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
    StepsContract.Presenter provideStepPresenter(Application application, EventBus bus) {
        return new StepsPresenter(application.getApplicationContext(), bus);
    }

    @Provides
    @Singleton
    edu.udacity.mou.bakingapp.ui.fragments.steps.StepsContract.Presenter provideStepsPresenter(Application application, StorageModel<Ingredient> storageModel) {
        return new edu.udacity.mou.bakingapp.ui.fragments.steps.StepsPresenter(application.getApplicationContext(), storageModel);
    }

    @Provides
    @Singleton
    RecipesContract.Presenter provideRecipesPresenter(Application application, EventBus bus) {
        return new RecipesPresenter(application.getApplicationContext(), bus);
    }

    @Provides
    @Singleton
    StepContract.Presenter provideStepSlidePresenter(Application application) {
        return new StepPresenter(application.getApplicationContext());
    }
}
