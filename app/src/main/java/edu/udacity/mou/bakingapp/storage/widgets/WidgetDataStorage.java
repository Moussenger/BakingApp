package edu.udacity.mou.bakingapp.storage.widgets;

/**
 * Created by mou on 26/05/17.
 */

public interface WidgetDataStorage {
    int loadRecipeId(int widgetId);
    String loadRecipeName(int widgetId);

    void saveData(int widgetId, int recipeId, String recipeName);
    void deleteData(int widgetId);

}
