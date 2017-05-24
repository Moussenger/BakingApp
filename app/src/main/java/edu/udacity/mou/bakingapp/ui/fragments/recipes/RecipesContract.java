package edu.udacity.mou.bakingapp.ui.fragments.recipes;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;

import edu.udacity.mou.bakingapp.model.Recipe;
import edu.udacity.mou.bakingapp.ui.BasePresenter;
import edu.udacity.mou.bakingapp.ui.BaseView;

/**
 * Created by mou on 23/05/17.
 */

public interface RecipesContract {
    interface View extends BaseView {
        void enableRefreshing(boolean refreshing);
        void setCursor(Cursor cursor);
    }

    interface Presenter extends BasePresenter<View> {
        void onSwipeRefresh();
        void create(AppCompatActivity activity);
        void register();
        void unregister();
        void onFirstLaunch();
    }
}
