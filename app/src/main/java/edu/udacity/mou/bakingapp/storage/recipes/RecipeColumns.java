package edu.udacity.mou.bakingapp.storage.recipes;

import net.simonvt.schematic.annotation.DataType;
import net.simonvt.schematic.annotation.PrimaryKey;

import static net.simonvt.schematic.annotation.DataType.Type.INTEGER;
import static net.simonvt.schematic.annotation.DataType.Type.TEXT;

/**
 * Created by mou on 23/05/17.
 */

public interface RecipeColumns {
    @DataType(INTEGER) @PrimaryKey String ID = "_id";
    @DataType(TEXT) String NAME = "name";
    @DataType(INTEGER) String SERVINGS = "servings";
    @DataType(TEXT) String IMAGE = "image";
}
