package edu.udacity.mou.bakingapp.ui.activitys.steps;

import android.content.Context;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by mou on 23/05/17.
 */

public class StepsPresenter implements StepsContract.Presenter {
    private Context context;
    private EventBus bus;
    private StepsContract.View view;

    public StepsPresenter(Context context, EventBus bus) {
        this.context = context;
        this.bus = bus;
    }

    @Override
    public void setView(StepsContract.View view) {
        this.view = view;
    }
}
