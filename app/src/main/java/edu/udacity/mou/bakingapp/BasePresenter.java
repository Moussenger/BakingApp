package edu.udacity.mou.bakingapp;

/**
 * Created by mou on 23/05/17.
 */

public interface BasePresenter<T extends BaseView> {
    void start();
    void stop();

    void resume();
    void pause();

    void setView(T view);
}
