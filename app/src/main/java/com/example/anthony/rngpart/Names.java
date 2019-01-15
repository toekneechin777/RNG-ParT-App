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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Names extends AppCompatActivity {

    List<String> namesList;
    private int drawNum;
    LinearLayout ll_names;
    private int screenWidth;
    private int namesID = 0;
    private boolean replace;

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
        replace = intent.getBooleanExtra("CHECK_REPLACE", true);
        ll_names = (LinearLayout) findViewById(R.id.ll_drawn);
        if(replace)
            draw();
        else
            draw2();
    }

    public void draw() {
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

    public void draw2() {
        List<String> givenList = new ArrayList<String>(namesList);
        Collections.shuffle(givenList);

        Iterator<String> it = givenList.iterator();


        for (int i = 0; i < drawNum; i++) {
            String randomElement = it.next();
            TextView drewName = new TextView(getApplicationContext());
            ViewGroup.LayoutParams newNamesParams = new ViewGroup.LayoutParams(3*screenWidth/4, ViewGroup.LayoutParams.WRAP_CONTENT);
            drewName.setLayoutParams(newNamesParams);
            drewName.setId(namesID);
            drewName.setText(randomElement);
            drewName.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
            drewName.setGravity(Gravity.CENTER);
            ll_names.addView(drewName);
            namesID++;
            it.remove();
        }
    }

    public void back(View v) {
        Intent returnToDraw = new Intent(this, DrawNames.class);
        startActivity(returnToDraw);
        finish();
    }
}
