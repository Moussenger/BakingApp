package edu.udacity.mou.bakingapp.ui.fragments.steps;

import android.database.Cursor;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.TextView;

import org.parceler.Parcels;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import edu.udacity.mou.bakingapp.R;
import edu.udacity.mou.bakingapp.model.Recipe;
import edu.udacity.mou.bakingapp.model.Step;
import edu.udacity.mou.bakingapp.ui.BasePresenter;
import edu.udacity.mou.bakingapp.ui.adapters.StepsAdapter;
import edu.udacity.mou.bakingapp.ui.adapters.holders.StepsViewHolder;
import edu.udacity.mou.bakingapp.ui.fragments.BakingAppFragment;
import edu.udacity.mou.bakingapp.ui.fragments.ingredients.IngredientsDialog;
import edu.udacity.mou.bakingapp.views.EmptyRecyclerView;
import icepick.State;

/**
 * Created by mou on 23/05/17.
 */

public class StepsFragment extends BakingAppFragment implements StepsContract.View, StepsViewHolder.OnStepClickListener {
    public static final String RECIPE_EXTRA = "RECIPE";
    private static final String INGREDIENTS_TAG = "INGREDIENTS";

    @Inject StepsContract.Presenter presenter;
    @Inject StepsAdapter adapter;

    @BindView(R.id.list) protected EmptyRecyclerView emptyRecyclerView;
    @BindView(R.id.empty_view) protected TextView emptyView;

    private OnStepClickListener onStepClickListener;

    public void setOnStepClickListener(OnStepClickListener listener) {
        onStepClickListener = listener;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_steps;
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
    public void setCursor(Cursor cursor) {
        adapter.setCursor(cursor);
    }

    @Override
    public void showIngredients(String ingredients) {
        IngredientsDialog dialog = IngredientsDialog.newInstance(ingredients);
        dialog.show(getFragmentManager(), INGREDIENTS_TAG);
    }

    @Override
    protected void onViewCreated() {
        configRecyclerView();
        Recipe recipe = loadRecipe();
        updateActionBar(recipe.getName());
        presenter.create((AppCompatActivity) getActivity(), recipe);
    }

    @OnClick(R.id.fragment_steps_button)
    public void onIngredientsClicked() {
        presenter.onIngredientsClicked(loadRecipe());
    }

    private Recipe loadRecipe() {
        Parcelable parcelable = getActivity().getIntent().getExtras().getParcelable(RECIPE_EXTRA);
        return Parcels.unwrap(parcelable);
    }

    private void updateActionBar(String title) {
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(title);
    }


    protected void configRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

        adapter.setOnStepClickListener(this);

        emptyRecyclerView.setHasFixedSize(true);
        emptyRecyclerView.setLayoutManager(layoutManager);
        emptyRecyclerView.setEmptyView(emptyView);
        emptyRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onStepClick(List<Step> steps, int position) {
        if(onStepClickListener != null) {
            onStepClickListener.onStepClick(steps, position);
            setPosition(position);
        }
    }

    public void setPosition (int position) {
        adapter.setSelected(position);
        emptyRecyclerView.scrollToPosition(position);
    }

    public interface OnStepClickListener {
        void onStepClick(List<Step> steps, int position);
    }
}
