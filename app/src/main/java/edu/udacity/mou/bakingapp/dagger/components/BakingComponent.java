package edu.udacity.mou.bakingapp.dagger.components;

import javax.inject.Singleton;

import dagger.Component;
import edu.udacity.mou.bakingapp.BakingApp;
import edu.udacity.mou.bakingapp.dagger.modules.AppModule;
import edu.udacity.mou.bakingapp.dagger.modules.MVPModule;
import edu.udacity.mou.bakingapp.dagger.modules.ManagerModule;
import edu.udacity.mou.bakingapp.dagger.modules.NetworkModule;
import edu.udacity.mou.bakingapp.dagger.modules.ServiceModule;
import edu.udacity.mou.bakingapp.dagger.modules.StorageModule;
import edu.udacity.mou.bakingapp.dagger.modules.UIModule;
import edu.udacity.mou.bakingapp.ui.activitys.main.MainActivity;
import edu.udacity.mou.bakingapp.ui.fragments.recipes.RecipesFragment;

/**
 * Created by mou on 22/05/17.
 */

@Singleton
@Component(modules={AppModule.class, NetworkModule.class, ServiceModule.class,
        ManagerModule.class, StorageModule.class, MVPModule.class, UIModule.class})
public interface BakingComponent {
    void inject(BakingApp application);

    void inject(MainActivity activity);

    void inject(RecipesFragment fragment);
}
