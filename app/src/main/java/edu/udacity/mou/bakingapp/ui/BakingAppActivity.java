package edu.udacity.mou.bakingapp.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import edu.udacity.mou.bakingapp.BakingApp;
import edu.udacity.mou.bakingapp.dagger.components.BakingComponent;

/**
 * Created by mou on 22/05/17.
 */

public abstract class BakingAppActivity extends AppCompatActivity {
    @Inject EventBus bus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //bus.register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        //bus.unregister(this);
    }

    protected BakingApp getBakingApp() {
        return ((BakingApp) getApplication());
    }

    protected BakingComponent getBakingComponent() {
        return getBakingApp().getBakingComponent();
    }

    protected abstract void inject();
}
