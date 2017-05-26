package edu.udacity.mou.bakingapp.ui.activitys.steps;

import edu.udacity.mou.bakingapp.ui.BasePresenter;
import edu.udacity.mou.bakingapp.ui.BaseView;

/**
 * Created by mou on 23/05/17.
 */

public interface StepsContract {
    interface View extends BaseView {
        boolean isTablet();
    }

    interface Presenter extends BasePresenter<View> {

    }
}
