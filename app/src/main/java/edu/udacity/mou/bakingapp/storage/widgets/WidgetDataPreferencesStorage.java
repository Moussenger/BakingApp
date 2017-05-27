package edu.udacity.mou.bakingapp.storage.widgets;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by mou on 26/05/17.
 */

public class WidgetDataPreferencesStorage implements WidgetDataStorage {
    private Context context;
    private static final String ID_PREFIX = "id_";
    private static final String NAME_PREFIX = "name_";

    public WidgetDataPreferencesStorage(Context context) {
        this.context = context;
    }


    private String getSharedPreferencesName() {
        return context.getPackageName();
    }

    private SharedPreferences getPreferences() {
        return context.getSharedPreferences(getSharedPreferencesName(), Context.MODE_PRIVATE);
    }

    private String getRecipeIdKey(int key) {
        return ID_PREFIX + key;
    }

    private String getNameKey(int key) {
        return NAME_PREFIX + key;
    }

    private SharedPreferences.Editor getPreferencesForSave() {
        return getPreferences().edit();
    }

    @Override
    public int loadRecipeId(int widgetId) {
        return getPreferences().getInt(getRecipeIdKey(widgetId), 0);
    }

    @Override
    public String loadRecipeName(int widgetId) {
        return getPreferences().getString(getNameKey(widgetId), "");
    }

    @Override
    public void saveData(int widgetId, int recipeId, String recipeName) {
        getPreferencesForSave()
                .putInt(getRecipeIdKey(widgetId), recipeId)
                .putString(getNameKey(widgetId), recipeName)
                .commit();
    }

    @Override
    public void deleteData(int widgetId) {
        getPreferencesForSave()
                .remove(getNameKey(widgetId))
                .remove(getRecipeIdKey(widgetId))
                .commit();
    }
}
