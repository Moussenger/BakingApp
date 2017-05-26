package edu.udacity.mou.bakingapp.ui.activitys.step;

import android.os.Bundle;

import org.parceler.Parcels;

import java.util.List;

import edu.udacity.mou.bakingapp.R;
import edu.udacity.mou.bakingapp.model.Step;
import edu.udacity.mou.bakingapp.ui.BasePresenter;
import edu.udacity.mou.bakingapp.ui.activitys.BakingAppActivity;
import edu.udacity.mou.bakingapp.ui.fragments.step.StepFragment;
import edu.udacity.mou.bakingapp.utils.ViewUtils;
import icepick.State;

/**
 * Created by mou on 25/05/17.
 */

public class StepActivity extends BakingAppActivity implements StepFragment.OnChangeStepListener {
    public static final String TITLE_EXTRA = "title";
    public static final String STEPS_EXTRA = "steps";
    public static final String POSITION_EXTRA = "position";

    @State Integer position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);

        getSupportActionBar().setTitle(getTitleExtra());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        configStep(getPosition());
    }

    private String getTitleExtra() {
        return getIntent().getStringExtra(TITLE_EXTRA);
    }

    private List<Step> getStepsExtra() {
        return Parcels.unwrap(getIntent().getParcelableExtra(STEPS_EXTRA));
    }

    private int getPosition() {
        if(position == null) {
            return getIntent().getIntExtra(POSITION_EXTRA, 0);
        } else {
            return position;
        }
    }

    private void configStep(int position) {
        List<Step> steps = getStepsExtra();

        ViewUtils.replaceStepFragment(getSupportFragmentManager(), R.id.step_fragment, steps, position, this);
    }

    @Override
    public BasePresenter configPresenter() {
        return null;
    }

    @Override
    protected void inject() {
        getBakingComponent().inject(this);
    }

    @Override
    public void onClickNextStep(int position) {
        List<Step> steps = getStepsExtra();

        if(++position < steps.size()) {
            configStep(position);
        }

        this.position = position;
    }

    @Override
    public void onClickPreviousStep(int position) {
        if(--position >= 0) {
            configStep(position);
        }

        this.position = position;
    }
}
