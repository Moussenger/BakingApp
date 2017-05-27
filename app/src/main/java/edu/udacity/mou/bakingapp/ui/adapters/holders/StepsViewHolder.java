package edu.udacity.mou.bakingapp.ui.adapters.holders;

import android.database.Cursor;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindColor;
import butterknife.BindView;
import edu.udacity.mou.bakingapp.R;
import edu.udacity.mou.bakingapp.model.Step;
import edu.udacity.mou.bakingapp.storage.StorageModel;

/**
 * Created by mou on 23/05/17.
 */

public class StepsViewHolder extends BaseViewHolder {
    @BindView(R.id.item_steps_layout) protected ViewGroup stepRow;
    @BindView(R.id.item_steps_short_description) protected TextView stepShortDescription;

    @BindColor(R.color.colorSelected) int selectedColor;

    private StorageModel<Step> storageModel;
    private OnStepClickListener stepClickListener;

    public StepsViewHolder(View itemView, StorageModel<Step> storageModel, OnStepClickListener listener) {
        super(itemView);
        this.storageModel = storageModel;
        this.stepClickListener = listener;
    }

    @Override
    public void bind(final Cursor cursor) {
        final Step step = storageModel.parseItem(cursor);

        stepRow.setBackgroundColor(Color.TRANSPARENT);
        stepShortDescription.setText(step.getShortDescription());
        stepRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(stepClickListener != null) {
                    List<Step> steps = storageModel.fromCursor(cursor);
                    stepClickListener.onStepClick(steps, step.getPosition());
                }
            }
        });
    }

    public void setSelected() {
        stepRow.setBackgroundColor(selectedColor);
    }

    public interface OnStepClickListener {
        void onStepClick(List<Step> steps, int position);
    }
}
