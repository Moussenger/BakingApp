package edu.udacity.mou.bakingapp.dagger.modules;

import android.app.Application;
import android.net.Uri;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import edu.udacity.mou.bakingapp.model.Ingredient;
import edu.udacity.mou.bakingapp.model.Recipe;
import edu.udacity.mou.bakingapp.model.Step;
import edu.udacity.mou.bakingapp.storage.StorageModel;
import edu.udacity.mou.bakingapp.storage.recipes.RecipesProvider;
import edu.udacity.mou.bakingapp.storage.storage_models.IngredientsStorageModel;
import edu.udacity.mou.bakingapp.storage.storage_models.RecipeStorageModel;
import edu.udacity.mou.bakingapp.storage.storage_models.StepStorageModel;

/**
 * Created by mou on 22/05/17.
 */

@Module
public class StorageModule {

    @Provides @Named("recipe_uri")
    @Singleton
    Uri provideRecipeUri() {
        return RecipesProvider.Recipes.CONTENT_URI;
    }

    @Provides @Named("ingredient_uri")
    @Singleton
    Uri provideIngredientUri() {
        return RecipesProvider.Ingredients.CONTENT_URI;
    }

    @Provides @Named("step_uri")
    @Singleton
    Uri provideStepUri() {
        return RecipesProvider.Steps.CONTENT_URI;
    }

    @Provides
    @Singleton
    StorageModel<Recipe> provideRecipeStorageModel(Application application, @Named("recipe_uri") Uri uri) {
        return new RecipeStorageModel(application.getApplicationContext(), uri);
    }

    @Provides
    @Singleton
    StorageModel<Ingredient> provideIngredientStorageModel(Application application, @Named("ingredient_uri") Uri uri) {
        return new IngredientsStorageModel(application.getApplicationContext(), uri);
    }

    @Provides
    @Singleton
    StorageModel<Step> provideStepStorageModel(Application application, @Named("step_uri") Uri uri) {
        return new StepStorageModel(application.getApplicationContext(), uri);
    }
}
