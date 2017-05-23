package edu.udacity.mou.bakingapp.storage.storage_models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import edu.udacity.mou.bakingapp.model.Step;
import edu.udacity.mou.bakingapp.storage.StorageModel;

import static edu.udacity.mou.bakingapp.storage.recipes.StepColumns.DESCRIPTION;
import static edu.udacity.mou.bakingapp.storage.recipes.StepColumns.ID;
import static edu.udacity.mou.bakingapp.storage.recipes.StepColumns.POSITION;
import static edu.udacity.mou.bakingapp.storage.recipes.StepColumns.RECIPE_ID;
import static edu.udacity.mou.bakingapp.storage.recipes.StepColumns.SHORT_DESCRIPTION;
import static edu.udacity.mou.bakingapp.storage.recipes.StepColumns.THUMBNAIL_URL;
import static edu.udacity.mou.bakingapp.storage.recipes.StepColumns.VIDEO_URL;
import static net.simonvt.schematic.Cursors.getInt;
import static net.simonvt.schematic.Cursors.getStringOrNull;

/**
 * Created by mou on 23/05/17.
 */

public class StepStorageModel extends StorageModel<Step> {

    public StepStorageModel(Context context, Uri contentUri) {
        super(context, contentUri);
    }

    @Override
    public ContentValues toContentValues(Step step) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(POSITION, step.getPosition());
        contentValues.put(THUMBNAIL_URL, step.getThumbnailURL());
        contentValues.put(VIDEO_URL, step.getVideoURL());
        contentValues.put(DESCRIPTION, step.getDescription());
        contentValues.put(SHORT_DESCRIPTION, step.getShortDescription());
        contentValues.put(RECIPE_ID, step.getRecipe().getId());

        return contentValues;
    }

    @Override
    public Step parseItem(Cursor cursor) {
        return Step.builder()
                .position(getInt(cursor, POSITION))
                .description(getStringOrNull(cursor, DESCRIPTION))
                .shortDescription(getStringOrNull(cursor, SHORT_DESCRIPTION))
                .videoURL(getStringOrNull(cursor, VIDEO_URL))
                .thumbnailURL(getStringOrNull(cursor, THUMBNAIL_URL))
                .build();
    }
}
