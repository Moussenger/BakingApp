package edu.udacity.mou.bakingapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.util.List;

import javax.inject.Inject;

import edu.udacity.mou.bakingapp.model.Ingredient;
import edu.udacity.mou.bakingapp.model.Recipe;
import edu.udacity.mou.bakingapp.storage.StorageModel;
import edu.udacity.mou.bakingapp.storage.recipes.RecipesProvider;
import edu.udacity.mou.bakingapp.utils.ViewUtils;

/**
 * Created by mou on 26/05/17.
 */

public class ListWidgetService extends RemoteViewsService {
    public static final String RECIPE_KEY = "recipe";

    @Inject StorageModel<Ingredient> ingredientStorageModel;
    @Inject StorageModel<Recipe> recipeStorageModel;

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        ((BakingApp) getApplication()).getBakingComponent().inject(this);
        return new ListRemoteViewsFactory(this.getApplicationContext(), getRecipe(intent), ingredientStorageModel);
    }

    private Recipe getRecipe(Intent intent) {
        int recipeId = intent.getIntExtra(RECIPE_KEY, 0);
        Uri uri = RecipesProvider.Recipes.withId(recipeId);
        Cursor cursor = getApplicationContext().getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        Recipe recipe = recipeStorageModel.parseItem(cursor);
        cursor.close();

        return recipe;
    }

    private class ListRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {
        private StorageModel<Ingredient> ingredientStorageModel;
        private Context context;
        private Recipe recipe;
        private List<Ingredient> ingredients;

        public ListRemoteViewsFactory(Context context, Recipe recipe, StorageModel<Ingredient> ingredientStorageModel) {
            this.context = context;
            this.recipe = recipe;
            this.ingredientStorageModel = ingredientStorageModel;
        }

        @Override
        public void onCreate() {

        }

        @Override
        public void onDataSetChanged() {
            Uri uri = RecipesProvider.Ingredients.fromRecipeWithId(recipe.getId());
            Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
            this.ingredients = ingredientStorageModel.fromCursor(cursor);
            cursor.close();
        }

        @Override
        public void onDestroy() {

        }

        @Override
        public int getCount() {
            return ingredients == null ? 0 : ingredients.size();
        }

        @Override
        public RemoteViews getViewAt(int position) {
            if(ingredients != null && ingredients.size() > 0) {
                Ingredient ingredient = ingredients.get(position);

                RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.item_widget_ingredients);
                views.setTextViewText(R.id.item_widget_ingredient, ViewUtils.getIngredientString(context, ingredient));

                return views;
            }

            return null;
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }
    }
}
