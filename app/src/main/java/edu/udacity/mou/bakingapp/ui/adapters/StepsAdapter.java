package edu.udacity.mou.bakingapp.ui.adapters;

import android.content.Context;
import android.view.View;

import edu.udacity.mou.bakingapp.R;
import edu.udacity.mou.bakingapp.model.Recipe;
import edu.udacity.mou.bakingapp.model.Step;
import edu.udacity.mou.bakingapp.storage.StorageModel;
import edu.udacity.mou.bakingapp.ui.adapters.holders.RecipeViewHolder;
import edu.udacity.mou.bakingapp.ui.adapters.holders.StepsViewHolder;

/**
 * Created by mou on 23/05/17.
 */

public class StepsAdapter extends BakingBaseAdapter<StepsViewHolder, Step> {
    private StepsViewHolder.OnStepClickListener onStepClickListener;
    private Integer selected;

    public StepsAdapter(Context context, StorageModel<Step> storageModel) {
        super(context, storageModel);
    }

    @Override
    public int getLayout() {
        return R.layout.item_steps;
    }

    @Override
    public StepsViewHolder createViewHolder(View item, StorageModel<Step> storageModel) {
        return new StepsViewHolder(item, storageModel, onStepClickListener);
    }

    @Override
    public void onBindViewHolder(StepsViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        Integer selected = getSelected();

        if(selected != null && selected == position) {
            holder.setSelected();
        }
    }

    public void setOnStepClickListener(StepsViewHolder.OnStepClickListener listener) {
        this.onStepClickListener = listener;
    }

    public Integer getSelected() {
        return selected;
    }

    public void setSelected(int selected) {
        this.selected = selected;
        notifyDataSetChanged();
    }
}
