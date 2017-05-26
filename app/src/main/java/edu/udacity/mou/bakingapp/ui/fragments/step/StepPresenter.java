package edu.udacity.mou.bakingapp.ui.fragments.step;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import edu.udacity.mou.bakingapp.R;
import edu.udacity.mou.bakingapp.model.Ingredient;
import edu.udacity.mou.bakingapp.model.Recipe;
import edu.udacity.mou.bakingapp.storage.StorageModel;
import edu.udacity.mou.bakingapp.storage.recipes.RecipesProvider;
import edu.udacity.mou.bakingapp.ui.fragments.steps.StepsContract;

/**
 * Created by mou on 23/05/17.
 */

public class StepPresenter implements StepContract.Presenter {
    private Context context;
    private StepContract.View view;

    public StepPresenter(Context context) {
        this.context = context;
    }

    @Override
    public void setView(StepContract.View view) {
        this.view = view;
    }
}

