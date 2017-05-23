package edu.udacity.mou.bakingapp.storage.recipes;

import net.simonvt.schematic.annotation.Database;
import net.simonvt.schematic.annotation.Table;

/**
 * Created by mou on 23/05/17.
 */
@Database(version = RecipesDatabase.VERSION)
public class RecipesDatabase {
    public static final int VERSION = 1;

    @Table(RecipeColumns.class) public static final String RECIPES = "recipes";
    @Table(IngredientColumns.class) public static final String INGREDIENTS = "ingredients";
    @Table(StepColumns.class) public static final String STEPS = "steps";
}
