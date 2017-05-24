package edu.udacity.mou.bakingapp.services.api.callbacks;

import android.content.Context;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import edu.udacity.mou.bakingapp.bus.events.RecipesGotEvent;
import edu.udacity.mou.bakingapp.model.Ingredient;
import edu.udacity.mou.bakingapp.model.Recipe;
import edu.udacity.mou.bakingapp.model.Step;
import edu.udacity.mou.bakingapp.storage.StorageModel;

/**
 * Created by mou on 23/05/17.
 */

public class GetRecipesCallback extends ApiServiceCallback<List<Recipe>> {

    private StorageModel<Recipe>  recipeStorageModel;
    private StorageModel<Ingredient> ingredientsStorageModel;
    private StorageModel<Step> stepStorageModel;

    public GetRecipesCallback(Context context, EventBus bus,
                              StorageModel<Recipe> recipeStorageModel,
                              StorageModel<Ingredient> ingredientsStorageModel,
                              StorageModel<Step> stepStorageModel) {
        super(context, bus);

        this.recipeStorageModel = recipeStorageModel;
        this.ingredientsStorageModel = ingredientsStorageModel;
        this.stepStorageModel = stepStorageModel;
    }

    @Override
    public void success(List<Recipe> recipes) {
        deleteAll();

        List<Ingredient> ingredients = new ArrayList<>();
        List<Step> steps = new ArrayList<>();

        for (Recipe recipe : recipes) {
            List<Ingredient> recipeIngredients = recipe.getIngredients();
            List<Step> recipeSteps = recipe.getSteps();

            setRecipeToIngredients(recipeIngredients, recipe);
            setRecipetoSteps(recipeSteps, recipe);

            ingredients.addAll(recipeIngredients);
            steps.addAll(recipeSteps);
        }

        saveAll(recipes, ingredients, steps);
        notify(true);
    }

    @Override
    public void error(Throwable t) {
        notify(false);
    }

    private void deleteAll() {
        recipeStorageModel.deleteAll();
        ingredientsStorageModel.deleteAll();
        stepStorageModel.deleteAll();
    }

    private void saveAll(List<Recipe> recipes, List<Ingredient> ingredients, List<Step> steps) {
        recipeStorageModel.save(recipes);
        ingredientsStorageModel.save(ingredients);
        stepStorageModel.save(steps);
    }

    private void setRecipeToIngredients(List<Ingredient> ingredients, Recipe recipe) {
        for (Ingredient ingredient : ingredients) {
            ingredient.setRecipe(recipe);
        }
    }

    private void setRecipetoSteps(List<Step> steps, Recipe recipe) {
        for (Step step : steps) {
            step.setRecipe(recipe);
        }
    }

    private void notify(boolean success) {
        getBus().post(RecipesGotEvent.builder().success(success).build());
    }
}
