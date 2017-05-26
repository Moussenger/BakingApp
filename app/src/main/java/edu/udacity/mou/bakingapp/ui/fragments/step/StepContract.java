package edu.udacity.mou.bakingapp.ui.fragments.step;

import edu.udacity.mou.bakingapp.ui.BasePresenter;
import edu.udacity.mou.bakingapp.ui.BaseView;

/**
 * Created by mou on 23/05/17.
 */

public interface StepContract {
    interface View extends BaseView {
    }

    interface Presenter extends BasePresenter<View> {

    }
}
