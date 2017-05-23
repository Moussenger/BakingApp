package edu.udacity.mou.bakingapp.services.api;

import edu.udacity.mou.bakingapp.api.BakingApi;
import edu.udacity.mou.bakingapp.services.api.callbacks.GetRecipesCallback;

/**
 * Created by mou on 23/05/17.
 */

public class ApiServiceImpl implements ApiService {
    private BakingApi api;
    private GetRecipesCallback recipesCallback;

    public ApiServiceImpl(BakingApi api, GetRecipesCallback recipesCallback) {
        this.api = api;
        this.recipesCallback = recipesCallback;
    }

    @Override
    public void recipes() {
        api.getRecipes().enqueue(recipesCallback);
    }
}
