package edu.udacity.mou.bakingapp.dagger.modules;

import android.app.Application;
import android.content.Context;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import edu.udacity.mou.bakingapp.api.BakingApi;
import edu.udacity.mou.bakingapp.model.Ingredient;
import edu.udacity.mou.bakingapp.model.Recipe;
import edu.udacity.mou.bakingapp.model.Step;
import edu.udacity.mou.bakingapp.services.api.ApiService;
import edu.udacity.mou.bakingapp.services.api.ApiServiceImpl;
import edu.udacity.mou.bakingapp.services.api.callbacks.GetRecipesCallback;
import edu.udacity.mou.bakingapp.storage.StorageModel;
import edu.udacity.mou.bakingapp.storage.storage_models.IngredientsStorageModel;
import edu.udacity.mou.bakingapp.storage.storage_models.RecipeStorageModel;
import edu.udacity.mou.bakingapp.storage.storage_models.StepStorageModel;

/**
 * Created by mou on 22/05/17.
 */

@Module
public class ServiceModule {

    @Provides
    @Singleton
    ApiService provideApiService(BakingApi api, GetRecipesCallback callback) {
        return new ApiServiceImpl(api, callback);
    }

    @Provides
    @Singleton
    GetRecipesCallback provideGetRecipesCallback(Application application, EventBus bus,
                                                 StorageModel<Recipe> recipeStorageModel,
                                                 StorageModel<Ingredient>  ingredientsStorageModel,
                                                 StorageModel<Step>  stepStorageModel) {
        return new GetRecipesCallback(application.getApplicationContext(), bus,
                recipeStorageModel, ingredientsStorageModel, stepStorageModel);
    }
}
