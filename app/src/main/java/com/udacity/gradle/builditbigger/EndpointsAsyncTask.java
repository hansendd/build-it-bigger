package com.udacity.gradle.builditbigger;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;
import com.udacity.jokedisplaymod.JokeActivity;

import java.io.IOException;

class EndpointsAsyncTask extends AsyncTask<Void, Void, String> {
    private MyApi myApiService = null;
    private Activity mActivity;

    // options for running against local devappserver
    // - 10.0.2.2 is localhost's IP address in Android emulator
    // - turn off compression when running against local devappserver
    private final String URL_ROOT = "http://10.0.2.2:8080/_ah/api/";

    public EndpointsAsyncTask(Activity activity) {
        mActivity = activity;
    }

    @Override
    protected String doInBackground(Void... params) {

        if (myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl(URL_ROOT)
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> request) {
                            request.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }

        try {
            return myApiService.tellJokes().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        Intent intent = new Intent(mActivity, JokeActivity.class);
        intent.putExtra("joke", result);
        mActivity.startActivity(intent);
    }
}

