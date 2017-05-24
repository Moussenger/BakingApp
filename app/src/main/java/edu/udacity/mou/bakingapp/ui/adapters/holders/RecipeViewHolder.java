package edu.udacity.mou.bakingapp.ui.adapters.holders;

import android.database.Cursor;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindString;
import butterknife.BindView;
import edu.udacity.mou.bakingapp.R;
import edu.udacity.mou.bakingapp.model.Recipe;
import edu.udacity.mou.bakingapp.storage.StorageModel;

/**
 * Created by mou on 23/05/17.
 */

public class RecipeViewHolder extends BaseViewHolder {
    @BindView(R.id.item_recipe_title_text) protected TextView recipeText;
    @BindView(R.id.item_recipe_servings_text) protected TextView servingsText;
    @BindView(R.id.item_recipe_card) protected CardView cardView;

    @BindString(R.string.servings) protected String servingsString;

    private StorageModel<Recipe> storageModel;
    private OnRecipeClickListener recipeClickListener;

    public RecipeViewHolder(View itemView, StorageModel<Recipe> storageModel, OnRecipeClickListener listener) {
        super(itemView);
        this.storageModel = storageModel;
        this.recipeClickListener = listener;
    }

    @Override
    public void bind(Cursor cursor) {
        final Recipe recipe = storageModel.parseItem(cursor);

        recipeText.setText(recipe.getName());
        servingsText.setText(String.format(servingsString, recipe.getServings()));
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(recipeClickListener != null) {
                    recipeClickListener.onRecipeClickListener(recipe);
                }
            }
        });
    }

    public interface OnRecipeClickListener {
        void onRecipeClickListener(Recipe recipe);
    }
}
