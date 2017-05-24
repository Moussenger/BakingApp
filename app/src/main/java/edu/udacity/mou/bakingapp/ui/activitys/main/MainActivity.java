package edu.udacity.mou.bakingapp.ui.activitys.main;

import android.os.Bundle;

import javax.inject.Inject;

import butterknife.BindView;
import edu.udacity.mou.bakingapp.model.Recipe;
import edu.udacity.mou.bakingapp.ui.BasePresenter;
import edu.udacity.mou.bakingapp.R;
import edu.udacity.mou.bakingapp.ui.activitys.BakingAppActivity;
import edu.udacity.mou.bakingapp.ui.fragments.recipes.RecipesFragment;

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
    public BasePresenter configPresenter() {
        presenter.setView(this);
        return presenter;
    }

    @Override
    public void onRecipeClick(Recipe recipe) {
        presenter.onRecipeClick(recipe);
    }
}
