package edu.udacity.mou.bakingapp.dagger.components;

import javax.inject.Singleton;

import dagger.Component;
import edu.udacity.mou.bakingapp.dagger.modules.AppModule;
import edu.udacity.mou.bakingapp.dagger.modules.NetworkModule;
import edu.udacity.mou.bakingapp.ui.MainActivity;

/**
 * Created by mou on 22/05/17.
 */

@Singleton
@Component(modules={AppModule.class, NetworkModule.class})
public interface BakingComponent {
    void inject(MainActivity activity);
}
