package edu.udacity.mou.bakingapp;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import javax.inject.Inject;

import edu.udacity.mou.bakingapp.model.Recipe;
import edu.udacity.mou.bakingapp.storage.widgets.WidgetDataStorage;
import edu.udacity.mou.bakingapp.ui.BasePresenter;
import edu.udacity.mou.bakingapp.ui.activitys.BakingAppActivity;
import edu.udacity.mou.bakingapp.ui.fragments.recipes.RecipesFragment;
import timber.log.Timber;

public class RecipeWidgetConfigureActivity extends BakingAppActivity implements RecipesFragment.OnRecipeClickListener {
    @Inject WidgetDataStorage widgetDataStorage;

    private RecipesFragment recipesFragment;
    private int appWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;

    public RecipeWidgetConfigureActivity() {
        super();
    }

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setResult(RESULT_CANCELED);
        setContentView(R.layout.recipe_widget_configure);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            appWidgetId = extras.getInt(
                    AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
        }

        if (appWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
            finish();
            return;
        }

        recipesFragment = (RecipesFragment) getSupportFragmentManager().findFragmentById(R.id.recipes_fragment);
        recipesFragment.setOnRecipeClickListener(this);
    }

    @Override
    protected void inject() {
        getBakingComponent().inject(this);
    }

    @Override
    public BasePresenter configPresenter() {
        return null;
    }

    @Override
    public void onRecipeClick(Recipe recipe) {
        final Context context = RecipeWidgetConfigureActivity.this;
        widgetDataStorage.saveData(appWidgetId, recipe.getId(), recipe.getName());

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        RecipeWidget.updateAppWidget(context, appWidgetManager, appWidgetId);

        Intent resultValue = new Intent();
        resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        setResult(RESULT_OK, resultValue);
        finish();
    }
}

