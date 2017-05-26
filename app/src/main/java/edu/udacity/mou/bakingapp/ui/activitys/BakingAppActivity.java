package edu.udacity.mou.bakingapp.ui.activitys;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

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
        Icepick.restoreInstanceState(this, savedInstanceState);
        this.presenter = configPresenter();
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected BakingApp getBakingApp() {
        return ((BakingApp) getApplication());
    }

    protected BakingComponent getBakingComponent() {
        return getBakingApp().getBakingComponent();
    }

    protected abstract void inject();
}
