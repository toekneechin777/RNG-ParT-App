package com.example.anthony.rngpart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class BasicDice extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_dice);
    }

    public void back(View v) {
        Intent returnToMenu = new Intent(this, MainMenu.class);
        startActivity(returnToMenu);
    }

    public void rng(View v) {
        EditText minBox = (EditText) findViewById(R.id.input_min);
        EditText maxBox = (EditText) findViewById(R.id.input_max);
        EditText intervalBox = (EditText) findViewById(R.id.input_interval);
        int vMin = Integer.valueOf(minBox.getText().toString());
        int vMax = Integer.valueOf(maxBox.getText().toString());
        double vInterval = Double.valueOf(intervalBox.getText().toString());
        final double rngAnswer = new Random().nextInt((int) Math.round((vMax-vMin)/vInterval))*vInterval + vMin;
        TextView result = (TextView) findViewById(R.id.result);
        result.setText(Double.toString(rngAnswer));
    }
}
