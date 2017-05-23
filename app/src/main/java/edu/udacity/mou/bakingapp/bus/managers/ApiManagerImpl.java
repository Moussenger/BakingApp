package edu.udacity.mou.bakingapp.bus.managers;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import edu.udacity.mou.bakingapp.bus.events.GetRecipesEvent;
import edu.udacity.mou.bakingapp.services.api.ApiService;

/**
 * Created by mou on 23/05/17.
 */

public class ApiManagerImpl implements ApiManager {
    private ApiService apiService;

    public ApiManagerImpl(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onGetRecipes(GetRecipesEvent event) {
        apiService.recipes();
    }
}
