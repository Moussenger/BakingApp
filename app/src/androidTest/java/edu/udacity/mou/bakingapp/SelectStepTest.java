package edu.udacity.mou.bakingapp;

import android.content.Intent;
import android.os.Parcelable;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;
import android.util.DisplayMetrics;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.parceler.Parcels;

import edu.udacity.mou.bakingapp.model.Recipe;
import edu.udacity.mou.bakingapp.storage.StorageModel;
import edu.udacity.mou.bakingapp.storage.storage_models.RecipeStorageModel;
import edu.udacity.mou.bakingapp.ui.activitys.step.StepActivity;
import edu.udacity.mou.bakingapp.ui.activitys.steps.StepsActivity;
import edu.udacity.mou.bakingapp.ui.fragments.steps.StepsFragment;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class SelectStepTest {

    @Rule public IntentsTestRule<StepsActivity> activityTestRule = new IntentsTestRule<StepsActivity>(StepsActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            Intent intent = super.getActivityIntent();
            Recipe recipe = Recipe.builder().id(1).name("Nutella Pie").build();
            intent.putExtra(StepsFragment.RECIPE_EXTRA, Parcels.wrap(recipe));
            return intent;
        }
    };


    @Test
    public void selectStep() throws Exception {
        onView(withId(R.id.list)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        if(isTablet()) {
            onView(withId(R.id.list)).check(matches(isDisplayed()));
        } else {
            intended(hasComponent(StepActivity.class.getName()));
        }

        onView(withId(R.id.fragment_step_description)).check(matches(isDisplayed()));
    }

    private boolean isTablet() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activityTestRule.getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        float widthDp = displayMetrics.widthPixels / displayMetrics.density;
        float heightDp = displayMetrics.heightPixels / displayMetrics.density;
        float screenSw = Math.min(widthDp, heightDp);
        return screenSw >= 600;
    }
}
