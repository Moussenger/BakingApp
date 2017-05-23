package edu.udacity.mou.bakingapp.ui.main;

import edu.udacity.mou.bakingapp.BasePresenter;
import edu.udacity.mou.bakingapp.BaseView;

/**
 * Created by mou on 23/05/17.
 */

public interface MainContract {
    interface View extends BaseView {

    }

    interface Presenter extends BasePresenter<View> {

    }
}
