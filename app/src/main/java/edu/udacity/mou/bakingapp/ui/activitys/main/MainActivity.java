package edu.udacity.mou.bakingapp.ui.activitys.main;

import android.content.Intent;
import android.os.Bundle;

import org.parceler.Parcels;

import javax.inject.Inject;

import edu.udacity.mou.bakingapp.model.Recipe;
import edu.udacity.mou.bakingapp.ui.BasePresenter;
import edu.udacity.mou.bakingapp.R;
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
}
