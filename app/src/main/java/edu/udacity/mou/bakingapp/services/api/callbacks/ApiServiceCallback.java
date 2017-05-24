package edu.udacity.mou.bakingapp.services.api.callbacks;

import android.content.Context;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

/**
 * Created by mou on 23/05/17.
 */

public abstract class ApiServiceCallback<T> implements Callback<T> {
    private Context context;
    private EventBus bus;

    public ApiServiceCallback(Context context, EventBus bus) {
        this.context = context;
        this.bus = bus;
    }

    @Override
    public void onResponse (Call<T> call, Response<T> response) {
        int code = response.code();
        String url = call.request().url().toString();

        if(200 <= code && code < 300) {
            processSuccess(response.body(), url, code);
        } else {
            processError(url, code);
        }
    }

    @Override
    public void onFailure (Call<T> call, Throwable t) {
        String url = call.request().url().toString();
        Timber.e("Error on call " + url + " => " + t.getMessage(), t);
        error(t);
    }

    private void processSuccess(T response, String url, int code) {
        Timber.i("Server response success (" + url + " - " + code + ")");
        success(response);
    }

    private void processError(String url, int code) {
        String message = "Server response with error: " + code;
        Exception e = new Exception(message);
        Timber.e("Error getting data from " + url + " + => " + e.getMessage(), e);
        error(e);
    }

    public Context getContext() {
        return context;
    }

    public EventBus getBus() {
        return bus;
    }

    public abstract void success(T response);
    public abstract void error(Throwable t);
}
