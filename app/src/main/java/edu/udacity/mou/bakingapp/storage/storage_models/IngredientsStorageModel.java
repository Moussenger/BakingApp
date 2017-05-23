package edu.udacity.mou.bakingapp.storage.storage_models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import edu.udacity.mou.bakingapp.model.Ingredient;
import edu.udacity.mou.bakingapp.storage.StorageModel;

import static edu.udacity.mou.bakingapp.storage.recipes.IngredientColumns.INGREDIENT;
import static edu.udacity.mou.bakingapp.storage.recipes.IngredientColumns.MEASURE;
import static edu.udacity.mou.bakingapp.storage.recipes.IngredientColumns.QUANTITY;
import static edu.udacity.mou.bakingapp.storage.recipes.IngredientColumns.RECIPE_ID;
import static net.simonvt.schematic.Cursors.getFloat;
import static net.simonvt.schematic.Cursors.getStringOrNull;

/**
 * Created by mou on 23/05/17.
 */

public class IngredientsStorageModel extends StorageModel<Ingredient> {

    public IngredientsStorageModel(Context context, Uri contentUri) {
        super(context, contentUri);
    }

    @Override
    public ContentValues toContentValues(Ingredient ingredient) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(INGREDIENT, ingredient.getIngredient());
        contentValues.put(MEASURE, ingredient.getMeasure());
        contentValues.put(QUANTITY, ingredient.getQuantity());
        contentValues.put(RECIPE_ID, ingredient.getRecipe().getId());

        return contentValues;
    }

    @Override
    public Ingredient parseItem(Cursor cursor) {
        return Ingredient.builder()
                .ingredient(getStringOrNull(cursor, INGREDIENT))
                .measure(getStringOrNull(cursor, MEASURE))
                .quantity(getFloat(cursor, QUANTITY))
                .build();
    }
}
