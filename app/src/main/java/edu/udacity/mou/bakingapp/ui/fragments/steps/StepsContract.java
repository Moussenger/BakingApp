package edu.udacity.mou.bakingapp.ui.fragments.steps;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;

import edu.udacity.mou.bakingapp.model.Recipe;
import edu.udacity.mou.bakingapp.ui.BasePresenter;
import edu.udacity.mou.bakingapp.ui.BaseView;

/**
 * Created by mou on 23/05/17.
 */

public interface StepsContract {
    interface View extends BaseView {
        void setCursor(Cursor cursor);
        void showIngredients(String ingredients);
    }

    interface Presenter extends BasePresenter<View> {
        void create(AppCompatActivity activity, Recipe recipe);
        void onIngredientsClicked(Recipe recipe);
    }
}
