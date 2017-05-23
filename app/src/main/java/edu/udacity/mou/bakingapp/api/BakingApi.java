package edu.udacity.mou.bakingapp.api;

import java.util.List;

import edu.udacity.mou.bakingapp.model.Recipe;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by mou on 22/05/17.
 */

public interface BakingApi {
    @GET("baking.json")
    Call<List<Recipe>> getRecipes();
}
