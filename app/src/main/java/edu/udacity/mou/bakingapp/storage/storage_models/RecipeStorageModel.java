package edu.udacity.mou.bakingapp.storage.storage_models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

import edu.udacity.mou.bakingapp.model.Recipe;
import edu.udacity.mou.bakingapp.storage.StorageModel;

import static edu.udacity.mou.bakingapp.storage.recipes.RecipeColumns.ID;
import static edu.udacity.mou.bakingapp.storage.recipes.RecipeColumns.IMAGE;
import static edu.udacity.mou.bakingapp.storage.recipes.RecipeColumns.NAME;
import static edu.udacity.mou.bakingapp.storage.recipes.RecipeColumns.SERVINGS;
import static net.simonvt.schematic.Cursors.getInt;
import static net.simonvt.schematic.Cursors.getStringOrNull;

/**
 * Created by mou on 23/05/17.
 */

public class RecipeStorageModel extends StorageModel<Recipe> {

    public RecipeStorageModel(Context context, Uri contentUri) {
        super(context, contentUri);
    }

    @Override
    public ContentValues toContentValues(Recipe recipe) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(ID, recipe.getId());
        contentValues.put(IMAGE, recipe.getImage());
        contentValues.put(NAME, recipe.getName());
        contentValues.put(SERVINGS, recipe.getServings());

        return contentValues;
    }

    @Override
    public Recipe parseItem(Cursor cursor) {
        return Recipe.builder()
                .id(getInt(cursor, ID))
                .image(getStringOrNull(cursor, IMAGE))
                .name(getStringOrNull(cursor, NAME))
                .servings(getInt(cursor, SERVINGS))
                .build();
    }
}
