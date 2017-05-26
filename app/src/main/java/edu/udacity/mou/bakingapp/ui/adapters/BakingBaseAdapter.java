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
import edu.udacity.mou.bakingapp.ui.adapters.holders.BaseViewHolder;
import edu.udacity.mou.bakingapp.ui.adapters.holders.RecipeViewHolder;

/**
 * Created by mou on 23/05/17.
 */

public abstract class BakingBaseAdapter<T extends BaseViewHolder, M> extends RecyclerView.Adapter<T> {
    private Context context;
    private Cursor cursor;
    private StorageModel<M> storageModel;

    public BakingBaseAdapter(Context context, StorageModel<M> storageModel) {
        this.context = context;
        this.storageModel = storageModel;
        this.cursor = null;
    }

    @Override
    public T onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(context).inflate(getLayout(), parent, false);

        return createViewHolder(item, storageModel);
    }

    @Override
    public void onBindViewHolder(T holder, int position) {
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

    public Context getContext() {
        return context;
    }

    public Cursor getCursor() {
        return cursor;
    }

    public StorageModel<M> getStorageModel() {
        return storageModel;
    }

    public abstract int getLayout();
    public abstract T createViewHolder(View item, StorageModel<M> storageModel);
}
