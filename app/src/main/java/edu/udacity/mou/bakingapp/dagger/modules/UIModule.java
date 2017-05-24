package edu.udacity.mou.bakingapp.dagger.modules;

import android.app.Application;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import edu.udacity.mou.bakingapp.api.BakingApi;
import edu.udacity.mou.bakingapp.model.Recipe;
import edu.udacity.mou.bakingapp.services.api.ApiService;
import edu.udacity.mou.bakingapp.services.api.ApiServiceImpl;
import edu.udacity.mou.bakingapp.services.api.callbacks.GetRecipesCallback;
import edu.udacity.mou.bakingapp.storage.StorageModel;
import edu.udacity.mou.bakingapp.storage.storage_models.IngredientsStorageModel;
import edu.udacity.mou.bakingapp.storage.storage_models.RecipeStorageModel;
import edu.udacity.mou.bakingapp.storage.storage_models.StepStorageModel;
import edu.udacity.mou.bakingapp.ui.adapters.RecipeAdapter;

/**
 * Created by mou on 22/05/17.
 */

@Module
public class UIModule {

    @Provides
    RecipeAdapter provideRecipeAdapter(Application application, StorageModel<Recipe> storageModel) {
        return new RecipeAdapter(application, storageModel);
    }
}
