package edu.udacity.mou.bakingapp.ui.activitys.steps;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.parceler.Parcels;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import edu.udacity.mou.bakingapp.R;
import edu.udacity.mou.bakingapp.model.Step;
import edu.udacity.mou.bakingapp.ui.BasePresenter;
import edu.udacity.mou.bakingapp.ui.activitys.BakingAppActivity;
import edu.udacity.mou.bakingapp.ui.activitys.step.StepActivity;
import edu.udacity.mou.bakingapp.ui.fragments.step.StepFragment;
import edu.udacity.mou.bakingapp.ui.fragments.steps.StepsFragment;
import edu.udacity.mou.bakingapp.utils.ViewUtils;
import icepick.Bundler;
import icepick.State;

/**
 * Created by mou on 24/05/17.
 */

public class StepsActivity extends BakingAppActivity implements StepsContract.View, StepsFragment.OnStepClickListener, StepFragment.OnChangeStepListener {
    @Inject StepsContract.Presenter presenter;

    @State protected int position;
    @State(StepBundler.class) protected List<Step> steps;

    @Nullable @BindView(R.id.step_fragment) ViewGroup stepLayout;

    @Nullable @BindView(R.id.step_warning) protected TextView stepWarningTextView;

    StepsFragment stepsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        stepsFragment = (StepsFragment) getSupportFragmentManager().findFragmentById(R.id.recipes_steps);
        stepsFragment.setOnStepClickListener(this);

        if(steps != null && isTablet()) {
            loadStepFragment(steps, position);
        }
    }

    @Override
    public BasePresenter configPresenter() {
        presenter.setView(this);
        return presenter;
    }

    @Override
    protected void inject() {
        getBakingComponent().inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(steps!= null) {
            stepsFragment.setPosition(position);
        }
    }

    @Override
    public void onStepClick(List<Step> steps, int position) {
        if(isTablet()) {
            this.position = position;
            this.steps = steps;
            loadStepFragment(steps, position);
        } else {
            goToStepActivity(steps, position);
        }
    }

    private void loadStepFragment(List<Step> steps, int position) {
        ViewUtils.replaceStepFragment(getSupportFragmentManager(), R.id.step_fragment, steps, position, this);
        stepWarningTextView.setVisibility(View.GONE);
    }

    private void goToStepActivity(List<Step> steps, int position) {
        Intent intent = new Intent(this, StepActivity.class);
        intent.putExtra(StepActivity.TITLE_EXTRA, getSupportActionBar().getTitle());
        intent.putExtra(StepActivity.STEPS_EXTRA, Parcels.wrap(steps));
        intent.putExtra(StepActivity.POSITION_EXTRA, position);

        startActivity(intent);
    }

    @Override
    public boolean isTablet() {
        return stepLayout != null;
    }

    @Override
    public void onClickNextStep(int position) {
        if(++position < steps.size()) {
            loadStepFragment(steps, position);
            stepsFragment.setPosition(position);
        }

        this.position = position;
    }

    @Override
    public void onClickPreviousStep(int position) {
        if(--position >= 0) {
            loadStepFragment(steps, position);
            stepsFragment.setPosition(position);
        }

        this.position = position;
    }
}

class StepBundler implements Bundler<List<Step>> {
    public void put(String key, List<Step> value, Bundle bundle) {
        bundle.putParcelable(key, Parcels.wrap(value));
    }

    public List<Step> get(String key, Bundle bundle) {
        return Parcels.unwrap(bundle.getParcelable(key));
    }
}
