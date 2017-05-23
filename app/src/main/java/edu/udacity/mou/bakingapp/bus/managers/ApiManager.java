package edu.udacity.mou.bakingapp.bus.managers;

import edu.udacity.mou.bakingapp.bus.events.GetRecipesEvent;

/**
 * Created by mou on 23/05/17.
 */

public interface ApiManager {
    void onGetRecipes(GetRecipesEvent event);
}
