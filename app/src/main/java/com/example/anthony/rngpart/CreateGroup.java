package com.example.anthony.rngpart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CreateGroup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.activity_create_group);
    }

    public void back(View v) {
        Intent returnToMenu = new Intent(this, MainMenu.class);
        startActivity(returnToMenu);
    }
}
