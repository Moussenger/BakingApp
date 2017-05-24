package edu.udacity.mou.bakingapp.ui.activitys.main;

import android.content.Context;

import org.greenrobot.eventbus.EventBus;

import edu.udacity.mou.bakingapp.model.Recipe;
import timber.log.Timber;

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
    public void onRecipeClick(Recipe recipe) {
        Timber.d(recipe.toString());
    }

    @Override
    public void setView(MainContract.View view) {
        this.view = view;
    }
}
