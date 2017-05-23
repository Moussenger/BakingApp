package edu.udacity.mou.bakingapp.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import butterknife.ButterKnife;
import edu.udacity.mou.bakingapp.BakingApp;
import edu.udacity.mou.bakingapp.BasePresenter;
import edu.udacity.mou.bakingapp.BaseView;
import edu.udacity.mou.bakingapp.dagger.components.BakingComponent;

/**
 * Created by mou on 22/05/17.
 */

public abstract class BakingAppActivity extends AppCompatActivity implements BaseView {
    @Inject EventBus bus;

    private BasePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject();
        ButterKnife.bind(this);
        this.presenter = configPresenter();
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.stop();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.resume();
    }

    protected BakingApp getBakingApp() {
        return ((BakingApp) getApplication());
    }

    protected BakingComponent getBakingComponent() {
        return getBakingApp().getBakingComponent();
    }

    protected abstract void inject();
}
