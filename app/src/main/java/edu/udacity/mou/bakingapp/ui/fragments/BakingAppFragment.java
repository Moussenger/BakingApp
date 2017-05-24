package edu.udacity.mou.bakingapp.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import edu.udacity.mou.bakingapp.BakingApp;
import edu.udacity.mou.bakingapp.dagger.components.BakingComponent;
import edu.udacity.mou.bakingapp.ui.BasePresenter;
import edu.udacity.mou.bakingapp.ui.BaseView;
import icepick.Icepick;

/**
 * Created by mou on 23/05/17.
 */

public abstract class BakingAppFragment extends Fragment implements BaseView {
    private BasePresenter presenter;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayout(), container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    protected void onViewCreated() {
        // Empty implementation
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        inject();
        presenter = configPresenter();
        Icepick.restoreInstanceState(this, savedInstanceState);
        onViewCreated();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter = null;
    }

    protected BakingComponent getBakingComponent() {
        return ((BakingApp)getActivity().getApplication()).getBakingComponent();
    }

    protected abstract void inject();
    protected abstract int getLayout();
}
