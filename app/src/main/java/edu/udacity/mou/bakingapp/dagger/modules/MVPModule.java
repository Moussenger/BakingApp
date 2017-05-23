package edu.udacity.mou.bakingapp.dagger.modules;

import android.app.Application;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import edu.udacity.mou.bakingapp.ui.main.MainContract;
import edu.udacity.mou.bakingapp.ui.main.MainPresenter;

/**
 * Created by mou on 22/05/17.
 */

@Module
public class MVPModule {

    @Provides
    @Singleton
    MainContract.Presenter provideMainPresenter(Application application, EventBus bus) {
        return new MainPresenter(application.getApplicationContext(), bus);
    }
}
