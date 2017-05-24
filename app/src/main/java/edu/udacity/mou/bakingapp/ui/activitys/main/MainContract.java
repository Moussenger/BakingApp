package edu.udacity.mou.bakingapp.ui.activitys.main;

import edu.udacity.mou.bakingapp.model.Recipe;
import edu.udacity.mou.bakingapp.ui.BasePresenter;
import edu.udacity.mou.bakingapp.ui.BaseView;

/**
 * Created by mou on 23/05/17.
 */

public interface MainContract {
    interface View extends BaseView {

    }

    interface Presenter extends BasePresenter<View> {
        void onRecipeClick(Recipe recipe);
    }
}
