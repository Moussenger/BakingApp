package edu.udacity.mou.bakingapp.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import javax.inject.Inject;

import edu.udacity.mou.bakingapp.BakingApp;
import edu.udacity.mou.bakingapp.R;
import retrofit2.Retrofit;

/**
 * Created by mou on 22/05/17.
 */

public class MainActivity extends BakingAppActivity{
    @Inject Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toast.makeText(this, retrofit.baseUrl().toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    protected void inject() {
        getBakingComponent().inject(this);
    }
}
