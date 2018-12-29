package com.example.anthony.rngpart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    public void enter(View v) {
        Intent goToActivity = new Intent();

        switch(v.getId()) {
            case R.id.rng0:
                goToActivity.setClass(this, BasicDice.class);
                break;
            case R.id.rng1:
                goToActivity.setClass(this,DrawNames.class);
                break;
            case R.id.rng2:
                goToActivity.setClass(this,CreateGroup.class);
                break;
        }

        startActivity(goToActivity);
    }
}
