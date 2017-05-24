package edu.udacity.mou.bakingapp.ui.adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.udacity.mou.bakingapp.R;
import edu.udacity.mou.bakingapp.model.Recipe;
import edu.udacity.mou.bakingapp.storage.StorageModel;
import edu.udacity.mou.bakingapp.ui.adapters.holders.RecipeViewHolder;

/**
 * Created by mou on 23/05/17.
 */

public class RecipeAdapter extends RecyclerView.Adapter<RecipeViewHolder> {
    private Context context;
    private Cursor cursor;
    private StorageModel<Recipe> storageModel;
    private RecipeViewHolder.OnRecipeClickListener onRecipeClickListener;

    public RecipeAdapter(Context context, StorageModel<Recipe> storageModel) {
        this.context = context;
        this.storageModel = storageModel;
        this.cursor = null;
    }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(context).inflate(R.layout.item_recipe, parent, false);

        return new RecipeViewHolder(item, storageModel, onRecipeClickListener);
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position) {
        cursor.moveToPosition(position);
        holder.bind(cursor);
    }

    @Override
    public int getItemCount() {
        return cursor == null ? 0 : cursor.getCount();
    }

    public void setCursor(Cursor cursor) {
        this.cursor = cursor;
        notifyDataSetChanged();
    }

    public void setOnRecipeClickListener(RecipeViewHolder.OnRecipeClickListener listener) {
        this.onRecipeClickListener = listener;
    }
}
