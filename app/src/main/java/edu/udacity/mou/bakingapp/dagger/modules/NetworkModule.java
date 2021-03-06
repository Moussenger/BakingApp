package edu.udacity.mou.bakingapp.dagger.modules;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import edu.udacity.mou.bakingapp.BuildConfig;
import edu.udacity.mou.bakingapp.api.BakingApi;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mou on 22/05/17.
 */

@Module
public class NetworkModule {

    @Provides @Named("server_url")
    @Singleton
    String provideServerUrl() {
        return BuildConfig.SERVER_URL;
    }

    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, @Named("server_url") String serverUrl) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(serverUrl)
                .build();
    }

    @Provides
    @Singleton
    BakingApi provideBakingApi(Retrofit retrofit) {
        return retrofit.create(BakingApi.class);
    }
}
