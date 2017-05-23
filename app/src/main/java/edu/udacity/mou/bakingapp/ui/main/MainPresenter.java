package edu.udacity.mou.bakingapp.ui.main;

import android.content.Context;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by mou on 23/05/17.
 */

public class MainPresenter implements MainContract.Presenter {
    private Context context;
    private EventBus bus;
    private MainContract.View view;

    public MainPresenter(Context context, EventBus bus) {
        this.context = context;
        this.bus = bus;
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void setView(MainContract.View view) {
        this.view = view;
    }
}
