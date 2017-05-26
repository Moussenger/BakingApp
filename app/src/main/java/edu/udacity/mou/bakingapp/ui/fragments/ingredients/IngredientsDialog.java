package edu.udacity.mou.bakingapp.ui.fragments.ingredients;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import edu.udacity.mou.bakingapp.R;

/**
 * Created by mou on 24/05/17.
 */

public class IngredientsDialog extends DialogFragment {
    private static final String INGREDIENTS_ARG = "ingredients";

    public static IngredientsDialog newInstance(String ingredientsList) {
        IngredientsDialog dialog = new IngredientsDialog();
        dialog.setArguments(getArgs(ingredientsList));

        return dialog;
    }

    private static Bundle getArgs(String ingredientsList) {
        Bundle bundle = new Bundle();
        bundle.putString(INGREDIENTS_ARG, ingredientsList);
        return bundle;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String ingredients = getArguments().getString(INGREDIENTS_ARG);

        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.ingredients)
                .setMessage(ingredients)
                .setPositiveButton(R.string.close, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
    }
}
