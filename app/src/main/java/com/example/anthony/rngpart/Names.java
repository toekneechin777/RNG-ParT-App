package com.example.anthony.rngpart;

import android.content.Intent;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

public class Names extends AppCompatActivity {

    List<String> namesList;
    private int drawNum;
    LinearLayout ll_names;
    private int screenWidth;
    private int namesID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_names);
        Intent intent = getIntent();
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidth = size.x;
        namesList = intent.getStringArrayListExtra("NAME_LIST");
        drawNum = intent.getIntExtra("DRAW_NUM", 1);
        ll_names = (LinearLayout) findViewById(R.id.ll_drawn);
        draw(ll_names);
    }

    /*
    public void givenList_whenNumberElementsChosen_shouldReturnRandomElementsNoRepeat() {
        Random rand = new Random();
        List<String> givenList;

        int numberOfElements = 2;

        for (int i = 0; i < numberOfElements; i++) {
            int randomIndex = rand.nextInt(givenList.size());
            String randomElement = givenList.get(randomIndex);
            givenList.remove(randomIndex);
        }
    } */

    public void draw(View v) {
        Random rand = new Random();


        for (int i = 0; i < drawNum; i++) {
            int randomIndex = rand.nextInt(namesList.size());
            String randomElement = namesList.get(randomIndex);
            TextView drewName = new TextView(getApplicationContext());
            ViewGroup.LayoutParams newNamesParams = new ViewGroup.LayoutParams(3*screenWidth/4, ViewGroup.LayoutParams.WRAP_CONTENT);
            drewName.setLayoutParams(newNamesParams);
            drewName.setId(namesID);
            drewName.setText(randomElement);
            drewName.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
            drewName.setGravity(Gravity.CENTER);
            ll_names.addView(drewName);
            namesID++;
        }
    }
}
