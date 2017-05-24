package edu.udacity.mou.bakingapp.ui.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import butterknife.ButterKnife;
import edu.udacity.mou.bakingapp.BakingApp;
import edu.udacity.mou.bakingapp.ui.BasePresenter;
import edu.udacity.mou.bakingapp.ui.BaseView;
import edu.udacity.mou.bakingapp.dagger.components.BakingComponent;
import icepick.Icepick;

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
        Icepick.restoreInstanceState(this, savedInstanceState);
        this.presenter = configPresenter();
    }

    @Override public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter = null;
    }

    protected BakingApp getBakingApp() {
        return ((BakingApp) getApplication());
    }

    protected BakingComponent getBakingComponent() {
        return getBakingApp().getBakingComponent();
    }

    protected abstract void inject();
}
