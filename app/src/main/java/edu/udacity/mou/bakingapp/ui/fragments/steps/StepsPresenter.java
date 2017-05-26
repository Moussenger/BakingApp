package edu.udacity.mou.bakingapp.ui.fragments.steps;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import edu.udacity.mou.bakingapp.R;
import edu.udacity.mou.bakingapp.model.Ingredient;
import edu.udacity.mou.bakingapp.model.Recipe;
import edu.udacity.mou.bakingapp.model.Step;
import edu.udacity.mou.bakingapp.storage.StorageModel;
import edu.udacity.mou.bakingapp.storage.recipes.RecipesProvider;
import timber.log.Timber;

/**
 * Created by mou on 23/05/17.
 */

public class StepsPresenter implements StepsContract.Presenter, LoaderManager.LoaderCallbacks<Cursor> {
    private static final int LOADER_ID = 2;
    private static final String RECIPE_ID = "recipe id";

    private Context context;
    private StepsContract.View view;
    private LoaderManager loaderManager;
    private StorageModel<Ingredient> ingredientStorageModel;

    public StepsPresenter(Context context, StorageModel<Ingredient> ingredientsStorageModel) {
        this.context = context;
        this.ingredientStorageModel = ingredientsStorageModel;
    }

    @Override
    public void create(AppCompatActivity activity, Recipe recipe) {
        loaderManager = activity.getSupportLoaderManager();
        initLoaderManager(recipe);
    }

    @Override
    public void onIngredientsClicked(Recipe recipe) {
        List<Ingredient> ingredientsList = ingredientStorageModel.fromCursor(getIngredients(recipe));
        view.showIngredients(generateIngredientsList(ingredientsList));
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Uri uri = RecipesProvider.Steps.fromRecipeWithId(args.getInt(RECIPE_ID));
        return new CursorLoader(context, uri, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        view.setCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        view.setCursor(null);
    }

    private void initLoaderManager(Recipe recipe) {
        Bundle bundle = new Bundle();
        bundle.putInt(RECIPE_ID, recipe.getId());

        loaderManager.initLoader(LOADER_ID, bundle, this);
    }

    @Override
    public void setView(StepsContract.View view) {
        this.view = view;
    }

    private Cursor getIngredients(Recipe recipe) {
        Uri uri = RecipesProvider.Ingredients.fromRecipeWithId(recipe.getId());
        return context.getContentResolver().query(uri, null, null, null, null);
    }

    private String generateIngredientsList(List<Ingredient> ingredients) {
        StringBuilder builder = new StringBuilder();
        String format = context.getString(R.string.ingredients_item);

        for (Ingredient ingredient : ingredients) {
            String item = String.format(format,
                    ingredient.getQuantity(),
                    ingredient.getMeasure(),
                    ingredient.getIngredient());

            builder.append(item);
        }
        
        return builder.toString();
    }
}

