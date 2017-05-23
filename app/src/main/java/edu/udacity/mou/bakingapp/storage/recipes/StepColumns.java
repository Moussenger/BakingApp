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

public interface StepColumns {
    @DataType(INTEGER) @PrimaryKey @AutoIncrement String ID = "_id";
    @DataType(INTEGER)  String POSITION = "position";
    @DataType(TEXT) String SHORT_DESCRIPTION = "short_description";
    @DataType(TEXT) String DESCRIPTION = "description";
    @DataType(TEXT) String VIDEO_URL = "video_url";
    @DataType(TEXT) String THUMBNAIL_URL = "thumbnail_url";

    @DataType(INTEGER)
    @References(table = RecipesDatabase.RECIPES, column = RecipeColumns.ID)
    String RECIPE_ID = "recipe_id";
}
