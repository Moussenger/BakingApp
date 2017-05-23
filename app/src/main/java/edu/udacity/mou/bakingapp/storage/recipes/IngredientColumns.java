package edu.udacity.mou.bakingapp.storage.recipes;

import net.simonvt.schematic.annotation.AutoIncrement;
import net.simonvt.schematic.annotation.DataType;
import net.simonvt.schematic.annotation.PrimaryKey;
import net.simonvt.schematic.annotation.References;

import static net.simonvt.schematic.annotation.DataType.Type.INTEGER;
import static net.simonvt.schematic.annotation.DataType.Type.REAL;
import static net.simonvt.schematic.annotation.DataType.Type.TEXT;

/**
 * Created by mou on 23/05/17.
 */

public interface IngredientColumns {
    @DataType(INTEGER) @PrimaryKey @AutoIncrement String ID = "_id";
    @DataType(REAL) String QUANTITY = "quantity";
    @DataType(TEXT) String MEASURE = "measure";
    @DataType(TEXT) String INGREDIENT = "ingredient";

    @DataType(INTEGER)
    @References(table = RecipesDatabase.RECIPES, column = RecipeColumns.ID)
    String RECIPE_ID = "recipe_id";
}
