package com.example.anthony.rngpart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class DrawNames extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_names);
    }

    public void back(View v) {
        Intent returnToMenu = new Intent(this, MainMenu.class);
        startActivity(returnToMenu);
    }
}
