package com.example.anthony.rngpart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class BasicDice extends AppCompatActivity {

    int[] rngColors;
    String[] rngColorNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.activity_basic_dice);
        rngColors = getResources().getIntArray(R.array.rng_colors);
        rngColorNames = getResources().getStringArray(R.array.rng_colors_name);
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

        if (vMax < vMin) {
            showError(minBox);
            return;
        }

        double vInterval = Double.valueOf(intervalBox.getText().toString());
        final double rngAnswer = new Random().nextInt((int) Math.round((vMax-vMin)/vInterval))*vInterval + vMin;
        TextView result = (TextView) findViewById(R.id.result);
        result.setText("You rolled: " + Double.toString(rngAnswer));
    }


    public void color_rng(View v) {
        TextView colorBox = (TextView) findViewById(R.id.color);
        int color_index = new Random().nextInt(rngColors.length);
        int randColor = rngColors[color_index];
        colorBox.setBackgroundColor(randColor);
        String randColorName = rngColorNames[color_index];
        colorBox.setText(randColorName);
    }

    public void flip_coin(View v) {
        TextView coinBox = (TextView) findViewById(R.id.coin);
        int coin_index = new Random().nextInt(2);
        if(coin_index != 0)
            coinBox.setText("Heads");
        else
            coinBox.setText("Tails");
    }

    private void showError(EditText et) {
        Animation shake = AnimationUtils.loadAnimation(this,R.anim.shake);
        et.startAnimation(shake);
        et.setError("Min must be smaller than Max");
    }


}
