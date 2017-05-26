package edu.udacity.mou.bakingapp.ui.adapters;

import android.content.Context;
import android.view.View;

import edu.udacity.mou.bakingapp.R;
import edu.udacity.mou.bakingapp.model.Recipe;
import edu.udacity.mou.bakingapp.storage.StorageModel;
import edu.udacity.mou.bakingapp.ui.adapters.holders.RecipeViewHolder;

/**
 * Created by mou on 23/05/17.
 */

public class RecipeAdapter extends BakingBaseAdapter<RecipeViewHolder, Recipe> {
    private RecipeViewHolder.OnRecipeClickListener onRecipeClickListener;

    public RecipeAdapter(Context context, StorageModel<Recipe> storageModel) {
        super(context, storageModel);
    }

    @Override
    public int getLayout() {
        return R.layout.item_recipe;
    }

    @Override
    public RecipeViewHolder createViewHolder(View item, StorageModel<Recipe> storageModel) {
        return new RecipeViewHolder(item, storageModel, onRecipeClickListener);
    }

    public void setOnRecipeClickListener(RecipeViewHolder.OnRecipeClickListener listener) {
        this.onRecipeClickListener = listener;
    }
}
