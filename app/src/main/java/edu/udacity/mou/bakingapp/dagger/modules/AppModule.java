package edu.udacity.mou.bakingapp.dagger.modules;

import android.app.Application;
import android.app.usage.UsageEvents;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mou on 22/05/17.
 */

@Module
public class AppModule {
    Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return this.application;
    }

    @Provides
    @Singleton
    EventBus provideEventBus() {
        EventBus.builder().sendNoSubscriberEvent(false).installDefaultEventBus();
        return EventBus.getDefault();
    }
}
