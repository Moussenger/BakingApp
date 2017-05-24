package edu.udacity.mou.bakingapp.ui.fragments.recipes;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import edu.udacity.mou.bakingapp.R;
import edu.udacity.mou.bakingapp.bus.events.GetRecipesEvent;
import edu.udacity.mou.bakingapp.bus.events.RecipesGotEvent;
import edu.udacity.mou.bakingapp.storage.recipes.RecipesProvider;

/**
 * Created by mou on 23/05/17.
 */

public class RecipesPresenter implements RecipesContract.Presenter, LoaderManager.LoaderCallbacks<Cursor> {
    private static final int LOADER_ID = 1;

    private Context context;
    private EventBus bus;
    private RecipesContract.View view;
    private LoaderManager loaderManager;

    public RecipesPresenter(Context context, EventBus bus) {
        this.context = context;
        this.bus = bus;
    }

    @Override
    public void setView(RecipesContract.View view) {
        this.view = view;
    }

    @Override
    public void onSwipeRefresh() {
        bus.post(GetRecipesEvent.builder().build());
    }

    @Override
    public void create(AppCompatActivity activity) {
        loaderManager = activity.getSupportLoaderManager();
        initLoaderManager();
    }

    @Override
    public void register() {
        bus.register(this);
    }

    @Override
    public void unregister() {
        bus.unregister(this);
    }

    @Override
    public void onFirstLaunch() {
        onSwipeRefresh();
        view.enableRefreshing(true);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(context, RecipesProvider.Recipes.CONTENT_URI, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        view.setCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        view.setCursor(null);
    }


    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onGotRecipes(RecipesGotEvent event) {
        bus.removeStickyEvent(event.getClass());
        view.enableRefreshing(false);
        restartLoaderManager();

        if(!event.isSuccess()) {
            onGotRecipesError();
        }
    }

    private void initLoaderManager() {
        loaderManager.initLoader(LOADER_ID, null, this);
    }

    private void restartLoaderManager() {
        loaderManager.restartLoader(LOADER_ID, null, this);
    }

    private void onGotRecipesError() {
        Toast.makeText(context, R.string.recipes_error_warning, Toast.LENGTH_LONG).show();
    }
}

