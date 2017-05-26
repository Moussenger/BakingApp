package edu.udacity.mou.bakingapp.utils;

import android.support.v4.app.FragmentManager;

import java.util.List;

import edu.udacity.mou.bakingapp.model.Step;
import edu.udacity.mou.bakingapp.ui.fragments.step.StepFragment;

/**
 * Created by mou on 25/05/17.
 */

public class ViewUtils {
    public static void replaceStepFragment(FragmentManager fragmentManager, int layout, List<Step> steps, int position, StepFragment.OnChangeStepListener listener) {
        boolean hasNext = steps.size() - 1 > position;
        boolean hasPrevious = position > 0;
        Step step = steps.get(position);

        StepFragment fragment = StepFragment.newInstance(step, position, hasNext, hasPrevious);
        fragment.setOnChangeStepListener(listener);
        fragmentManager.beginTransaction().replace(layout, fragment).commit();
    }
}
