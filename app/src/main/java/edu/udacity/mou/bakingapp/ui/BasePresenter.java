package edu.udacity.mou.bakingapp.ui;

/**
 * Created by mou on 23/05/17.
 */

public interface BasePresenter<T extends BaseView> {
    void setView(T view);
}
