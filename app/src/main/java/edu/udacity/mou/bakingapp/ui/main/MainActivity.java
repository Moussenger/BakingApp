package edu.udacity.mou.bakingapp.ui.main;

import android.graphics.Color;
import android.os.Bundle;

import javax.inject.Inject;

import edu.udacity.mou.bakingapp.BasePresenter;
import edu.udacity.mou.bakingapp.R;
import edu.udacity.mou.bakingapp.ui.BakingAppActivity;

/**
 * Created by mou on 22/05/17.
 */

public class MainActivity extends BakingAppActivity implements MainContract.View {
    @Inject MainContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
    }

    @Override
    protected void inject() {
        getBakingComponent().inject(this);
    }

    @Override
    public BasePresenter configPresenter() {
        presenter.setView(this);
        return presenter;
    }
}
