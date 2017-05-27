package edu.udacity.mou.bakingapp;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import javax.inject.Inject;

import edu.udacity.mou.bakingapp.storage.widgets.WidgetDataPreferencesStorage;
import edu.udacity.mou.bakingapp.storage.widgets.WidgetDataStorage;
import edu.udacity.mou.bakingapp.ui.activitys.step.StepActivity;
import edu.udacity.mou.bakingapp.ui.activitys.steps.StepsActivity;
import edu.udacity.mou.bakingapp.ui.fragments.steps.StepsFragment;

public class RecipeWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        WidgetDataStorage storage = new WidgetDataPreferencesStorage(context);
        String recipeName = storage.loadRecipeName(appWidgetId);
        int recipeId = storage.loadRecipeId(appWidgetId);

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.recipe_widget);

        configWidgetViews(context, views, recipeName, recipeId);

        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    static void configWidgetViews(Context context, RemoteViews views, String recipeName, int recipeId) {
        views.setTextViewText(R.id.appwidget_title, recipeName);

        Intent intent = new Intent(context, ListWidgetService.class);
        intent.putExtra(ListWidgetService.RECIPE_KEY, recipeId);
        views.setRemoteAdapter(R.id.appwidget_steps, intent);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        WidgetDataStorage storage = new WidgetDataPreferencesStorage(context);

        for (int appWidgetId : appWidgetIds) {
            storage.deleteData(appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {

    }

    @Override
    public void onDisabled(Context context) {
    }
}

