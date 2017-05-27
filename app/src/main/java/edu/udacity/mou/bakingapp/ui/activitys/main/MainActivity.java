package edu.udacity.mou.bakingapp.ui.activitys.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;

import org.parceler.Parcels;

import javax.inject.Inject;

import edu.udacity.mou.bakingapp.model.Recipe;
import edu.udacity.mou.bakingapp.ui.BasePresenter;
import edu.udacity.mou.bakingapp.R;
import edu.udacity.mou.bakingapp.ui.SimpleIdlingResource;
import edu.udacity.mou.bakingapp.ui.activitys.BakingAppActivity;
import edu.udacity.mou.bakingapp.ui.activitys.steps.StepsActivity;
import edu.udacity.mou.bakingapp.ui.fragments.recipes.RecipesFragment;
import edu.udacity.mou.bakingapp.ui.fragments.steps.StepsFragment;

/**
 * Created by mou on 22/05/17.
 */

public class MainActivity extends BakingAppActivity implements MainContract.View, RecipesFragment.OnRecipeClickListener {
    @Inject MainContract.Presenter presenter;

    private RecipesFragment recipesFragment;

    @Nullable private SimpleIdlingResource idlingResource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        recipesFragment = (RecipesFragment) getSupportFragmentManager().findFragmentById(R.id.recipes_fragment);
        recipesFragment.setOnRecipeClickListener(this);
    }

    @Override
    protected void inject() {
        getBakingComponent().inject(this);
    }

    @Override
    public void goToStepActivity(Recipe recipe) {
        Intent intent = new Intent(this, StepsActivity.class);
        intent.putExtra(StepsFragment.RECIPE_EXTRA, Parcels.wrap(recipe));

        startActivity(intent);
    }

    @Override
    public BasePresenter configPresenter() {
        presenter.setView(this);
        return presenter;
    }

    @Override
    public void onRecipeClick(Recipe recipe) {
        presenter.onRecipeClick(recipe);
    }

    private void setIdLingResource(boolean value) {
        if(idlingResource != null) {
            idlingResource.setIdleState(value);
        }
    }

    @VisibleForTesting
    @NonNull
    public IdlingResource getIdlingResource() {
        if (idlingResource == null) {
            SimpleIdlingResource simpleIdlingResource = new SimpleIdlingResource();
            recipesFragment.setIdlingResource(simpleIdlingResource);
            idlingResource = simpleIdlingResource;
        }

        return idlingResource;
    }
}
