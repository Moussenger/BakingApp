package edu.udacity.mou.bakingapp.ui.fragments.recipes;

import android.database.Cursor;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindInt;
import butterknife.BindView;
import edu.udacity.mou.bakingapp.R;
import edu.udacity.mou.bakingapp.model.Recipe;
import edu.udacity.mou.bakingapp.ui.BasePresenter;
import edu.udacity.mou.bakingapp.ui.adapters.RecipeAdapter;
import edu.udacity.mou.bakingapp.ui.adapters.holders.RecipeViewHolder;
import edu.udacity.mou.bakingapp.ui.fragments.BakingAppFragment;
import edu.udacity.mou.bakingapp.views.EmptyRecyclerView;
import icepick.State;

/**
 * Created by mou on 23/05/17.
 */

public class RecipesFragment extends BakingAppFragment implements RecipesContract.View,
        SwipeRefreshLayout.OnRefreshListener, RecipeViewHolder.OnRecipeClickListener {
    @Inject RecipesContract.Presenter presenter;
    @Inject RecipeAdapter adapter;

    @BindView(R.id.swipe_refresh) protected SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.list) protected EmptyRecyclerView emptyRecyclerView;
    @BindView(R.id.empty_view) protected TextView emptyView;

    @BindInt(R.integer.recipes_columns) protected int recipesColumns;

    @State protected boolean refreshing;
    @State protected boolean firstLaunch = true;

    private OnRecipeClickListener onRecipeClickListener;

    public void setOnRecipeClickListener(OnRecipeClickListener listener) {
        onRecipeClickListener = listener;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_recipes;
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
    public void onStart() {
        super.onStart();
        presenter.register();
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.unregister();
    }

    @Override
    public void onRefresh() {
        presenter.onSwipeRefresh();
        this.refreshing = true;
    }

    @Override
    public void enableRefreshing(boolean refreshing) {
        swipeRefreshLayout.setRefreshing(refreshing);
        this.refreshing = refreshing;
    }

    @Override
    public void setCursor(Cursor cursor) {
        adapter.setCursor(cursor);
    }

    @Override
    protected void onViewCreated() {
        configRecyclerView();
        configSwipe();
        presenter.create((AppCompatActivity) getActivity());

        if(firstLaunch) {
            presenter.onFirstLaunch();
            firstLaunch = false;
        }
    }


    protected void configRecyclerView() {
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), recipesColumns);

        adapter.setOnRecipeClickListener(this);

        emptyRecyclerView.setHasFixedSize(true);
        emptyRecyclerView.setLayoutManager(layoutManager);
        emptyRecyclerView.setEmptyView(emptyView);
        emptyRecyclerView.setAdapter(adapter);
    }

    protected void configSwipe () {
        swipeRefreshLayout.setOnRefreshListener(this);
        enableRefreshing(refreshing);
    }

    @Override
    public void onRecipeClickListener(Recipe recipe) {
        if(onRecipeClickListener != null) {
            onRecipeClickListener.onRecipeClick(recipe);
        }
    }

    public interface OnRecipeClickListener {
        void onRecipeClick(Recipe recipe);
    }

}
