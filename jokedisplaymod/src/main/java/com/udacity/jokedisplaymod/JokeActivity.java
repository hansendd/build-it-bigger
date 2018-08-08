package com.udacity.jokedisplaymod;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        TextView jokeTextview = findViewById(R.id.joke_textview);
        String joke = getIntent().getStringExtra("joke");

        jokeTextview.setText(joke);
    }
}
