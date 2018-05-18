package com.example.rotciv.fifa_daves;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import pl.droidsonroids.gif.GifImageView;

public class GifActivity extends AppCompatActivity {

    GifImageView gif;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gif);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                finish();
                overridePendingTransition(0, android.R.anim.fade_out);
            }
        }, 2400);

    }

}
