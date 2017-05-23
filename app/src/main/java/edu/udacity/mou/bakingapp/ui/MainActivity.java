package edu.udacity.mou.bakingapp.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import edu.udacity.mou.bakingapp.BakingApp;
import edu.udacity.mou.bakingapp.R;
import edu.udacity.mou.bakingapp.api.BakingApi;
import edu.udacity.mou.bakingapp.bus.events.GetRecipesEvent;
import edu.udacity.mou.bakingapp.model.Recipe;
import edu.udacity.mou.bakingapp.services.api.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by mou on 22/05/17.
 */

public class MainActivity extends BakingAppActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        bus.post(new GetRecipesEvent());
    }

    @Override
    protected void inject() {
        getBakingComponent().inject(this);
    }
}
