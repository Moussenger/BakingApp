package edu.udacity.mou.bakingapp.dagger.modules;

import android.app.Application;

import dagger.Module;
import dagger.Provides;
import edu.udacity.mou.bakingapp.model.Recipe;
import edu.udacity.mou.bakingapp.model.Step;
import edu.udacity.mou.bakingapp.storage.StorageModel;
import edu.udacity.mou.bakingapp.ui.adapters.RecipeAdapter;
import edu.udacity.mou.bakingapp.ui.adapters.StepsAdapter;

/**
 * Created by mou on 22/05/17.
 */

@Module
public class UIModule {

    @Provides
    RecipeAdapter provideRecipeAdapter(Application application, StorageModel<Recipe> storageModel) {
        return new RecipeAdapter(application.getApplicationContext(), storageModel);
    }

    @Provides
    StepsAdapter provideStepsAdapter(Application application, StorageModel<Step> storageModel) {
        return new StepsAdapter(application.getApplicationContext(), storageModel);
    }
}
