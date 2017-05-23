package edu.udacity.mou.bakingapp.storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

/**
 * Created by mou on 23/05/17.
 */

public abstract class StorageModel<T> {
    @Getter private Context context;
    @Getter private Uri contentUri;

    public StorageModel(Context context, Uri contentUri) {
        this.context = context;
        this.contentUri = contentUri;
    }

    public ContentValues[] toContentValues(List<T> models) {
        ContentValues[] contentValues = new ContentValues[models.size()];

        for (int i = 0; i < models.size(); i++) {
            contentValues[i] = toContentValues(models.get(i));
        }

        return contentValues;
    }

    public List<T> fromCursor(Cursor cursor) {
        List<T> models = new ArrayList<>();
        if(cursor.moveToFirst()) {
            do {
                models.add(parseItem(cursor));
            } while(cursor.moveToNext());
        }

        return models;
    }

    public void save(T model) {
        context.getContentResolver().insert(contentUri, toContentValues(model));
    }

    public void save(List<T> models) {
        context.getContentResolver().bulkInsert(contentUri, toContentValues(models));
    }

    public void deleteAll() {
        context.getContentResolver().delete(contentUri, null, null);
    }

    public abstract ContentValues toContentValues(T model);
    public abstract T parseItem(Cursor cursor);


}
