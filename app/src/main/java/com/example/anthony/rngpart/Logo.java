package com.example.anthony.rngpart;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Logo extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);
        getWindow().getDecorView().setBackgroundColor(Color.WHITE);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(Logo.this, MainMenu.class);
                Logo.this.startActivity(mainIntent);
                Logo.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
