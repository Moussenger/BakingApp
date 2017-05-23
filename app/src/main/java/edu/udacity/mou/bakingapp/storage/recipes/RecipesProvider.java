package edu.udacity.mou.bakingapp.storage.recipes;

import android.net.Uri;

import net.simonvt.schematic.annotation.ContentProvider;
import net.simonvt.schematic.annotation.ContentUri;
import net.simonvt.schematic.annotation.InexactContentUri;
import net.simonvt.schematic.annotation.TableEndpoint;

import edu.udacity.mou.bakingapp.BuildConfig;

import static edu.udacity.mou.bakingapp.storage.recipes.ProviderUtils.buildUri;

/**
 * Created by mou on 23/05/17.
 */
interface Name {
    String INGREDIENTS_FROM_RECIPE = "INGREDIENTS_FROM_RECIPE";
    String STEPS_FROM_RECIPE = "STEPS_FROM_RECIPE";
}

interface Path {
    String INEXACT = "/#";

    String RECIPES = "recipes";
    String INGREDIENTS = "ingredients";
    String STEPS = "steps";

    String INGREDIENTS_FROM_RECIPE = INGREDIENTS + "/fromRecipe" + INEXACT;
    String STEPS_FROM_RECIPE = STEPS + "/fromRecipe" + INEXACT;
}

interface Types {
    String DIR_TYPE = "vnd.android.cursor.dir/";
    String ITEM_TYPE = "vnd.android.cursor.item/";

    String RECIPES = "bkRecipe";
    String RECIPES_DIR = DIR_TYPE + RECIPES;

    String INGREDIENTS = "bkIngredient";
    String INGREDIENTS_DIR = DIR_TYPE + INGREDIENTS;

    String STEPS = "bkStep";
    String STEPS_DIR = DIR_TYPE + STEPS;
}

interface Sort {
    String RECIPES = RecipeColumns.ID + " ASC";
    String INGREDIENTS = IngredientColumns.ID + " ASC";
    String STEPS = StepColumns.ID + " ASC";
}

@ContentProvider(authority = BuildConfig.PROVIDER_AUTHORITY, database = RecipesDatabase.class)
public class RecipesProvider {

    @TableEndpoint(table = RecipesDatabase.RECIPES)
    public static class Recipes {
        @ContentUri(
                path = Path.RECIPES,
                type = Types.RECIPES_DIR,
                defaultSort = Sort.RECIPES
        )
        public static Uri CONTENT_URI = buildUri(Path.RECIPES);
    }

    @TableEndpoint(table = RecipesDatabase.INGREDIENTS)
    public static class Ingredients {
        @ContentUri(
                path = Path.INGREDIENTS,
                type = Types.INGREDIENTS_DIR,
                defaultSort = Sort.INGREDIENTS
        )
        public static Uri CONTENT_URI = buildUri(Path.INGREDIENTS);

        @InexactContentUri(
                name = Name.INGREDIENTS_FROM_RECIPE,
                path = Path.INGREDIENTS_FROM_RECIPE,
                type = Types.INGREDIENTS_DIR,
                whereColumn = IngredientColumns.RECIPE_ID,
                pathSegment = 2)
        public static Uri withId(long id) {
            return buildUri(Path.INGREDIENTS_FROM_RECIPE, String.valueOf(id));
        }
    }

    @TableEndpoint(table = RecipesDatabase.STEPS)
    public static class Steps {
        @ContentUri(
                path = Path.STEPS,
                type = Types.STEPS_DIR,
                defaultSort = Sort.STEPS
        )
        public static Uri CONTENT_URI = buildUri(Path.STEPS);

        @InexactContentUri(
                name = Name.STEPS_FROM_RECIPE,
                path = Path.STEPS_FROM_RECIPE,
                type = Types.STEPS_DIR,
                whereColumn = StepColumns.RECIPE_ID,
                pathSegment = 2)
        public static Uri withId(long id) {
            return buildUri(Path.STEPS_FROM_RECIPE, String.valueOf(id));
        }
    }
}

class ProviderUtils {
    private static final Uri BASE_CONTENT_URI = Uri.parse("content://"+ BuildConfig.PROVIDER_AUTHORITY);

    public static Uri buildUri(String... paths) {
        Uri.Builder builder = BASE_CONTENT_URI.buildUpon();

        for(String path : paths) {
            builder.appendPath(path);
        }

        return builder.build();
    }
}
