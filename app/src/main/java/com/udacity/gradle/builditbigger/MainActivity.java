package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.R;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;
import com.udacity.jokedisplaymod.JokeActivity;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setTitle(getResources().getString(R.string.title));
    }

    public void tellJoke(View view) {
        progressBar.setVisibility(View.VISIBLE);
        new EndpointsAsyncTask(this).execute();
    }

    @Override
    protected void onPause() {
        super.onPause();
        progressBar.setVisibility(View.GONE);
    }
}
