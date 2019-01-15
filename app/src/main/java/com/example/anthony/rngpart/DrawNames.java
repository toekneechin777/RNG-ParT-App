package com.example.anthony.rngpart;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DrawNames extends AppCompatActivity {
    private Spinner numNames;
    private int numOfNames;
    public int namesID = 1;
    private int screenWidth;
    LinearLayout ll;
    EditText drawNumBox;
    List<String> namesList;
    private int tabIdx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.activity_draw_names);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidth = size.x;
        numNames = (Spinner)findViewById(R.id.num_names);
        drawNumBox = (EditText) findViewById(R.id.num_draw);
        ll = (LinearLayout) findViewById(R.id.ll_names);
        ArrayAdapter<String> numNamesList = new ArrayAdapter<String>(this   ,android.R.layout.simple_spinner_item,
                                                                        getResources().getStringArray(R.array.draw_names_num_options));
        numNamesList.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        numNames.setAdapter(numNamesList);
        numNames.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                clearNames(ll);

                if(position == 0) {
                    // Do nothing
                    numOfNames = -1;
                } else {
                    Toast.makeText(parent.getContext(), "You chose " + parent.getItemAtPosition(position).toString() + " num of names",
                            Toast.LENGTH_SHORT).show();
                    numOfNames = Integer.parseInt(parent.getItemAtPosition(position).toString());

                    for(int i = 0; i < numOfNames; i++) {
                        EditText newName = new EditText(getApplicationContext());
                        ViewGroup.LayoutParams newNamesParams = new ViewGroup.LayoutParams(3*screenWidth/4, ViewGroup.LayoutParams.WRAP_CONTENT);
                        newName.setLayoutParams(newNamesParams);
                        newName.setId(namesID);
                        newName.setHint("Enter a name #" + (i+1));
                        newName.setGravity(Gravity.CENTER);
                        ll.addView(newName);
                        namesID++;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        if(savedInstanceState != null) {
            drawNumBox.setText(savedInstanceState.getString("DRAWNUM"), TextView.BufferType.EDITABLE);
            int pos = savedInstanceState.getInt("NUMNAMES_POS");
            numNames.setSelection(pos);
            namesList = savedInstanceState.getStringArrayList("NAMESLIST");
            int group_num = ll.getChildCount();
            int viewindex = 0;
            EditText ename;
            Iterator<String> it = namesList.iterator();
            for(int i = 0; i < group_num; i++) {
                View view = ll.getChildAt(viewindex);
                if(view instanceof EditText) {
                    ename = (EditText) view;
                    ename.setText(it.next());
                }
                else
                    viewindex++;
            }
        } else {
            namesList = new ArrayList<String>();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("DRAWNUM", drawNumBox.getText().toString());
        savedInstanceState.putInt("NUMNAMES_POS", numNames.getSelectedItemPosition());
        savedInstanceState.putStringArrayList("NAMESLIST", new ArrayList<String>(namesList));
        Toast.makeText(this, "Saved State",
                Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        drawNumBox.setText(savedInstanceState.getString("DRAWNUM"), TextView.BufferType.EDITABLE);
        int pos = savedInstanceState.getInt("NUMNAMES_POS");
        numNames.setSelection(pos);
        namesList = savedInstanceState.getStringArrayList("NAMESLIST");
        int group_num = ll.getChildCount();
        int viewindex = 0;
        EditText ename;
        Iterator<String> it = namesList.iterator();
        for(int i = 0; i < group_num; i++) {
            View view = ll.getChildAt(viewindex);
            if(view instanceof EditText) {
                ename = (EditText) view;
                ename.setText(it.next());
            }
            else
                viewindex++;
        }
        Toast.makeText(this, "Restored State",
                Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences settings = getSharedPreferences("MYPREF", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("DRAWNUM", drawNumBox.getText().toString());
        editor.commit();
        Toast.makeText(this, "Paused State",
                Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences settings = getSharedPreferences("MYPREF", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("DRAWNUM", drawNumBox.getText().toString());
        editor.commit();
        Toast.makeText(this, "Stop State",
                Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        SharedPreferences settings = getSharedPreferences("MYPREF", 0);
        drawNumBox.setText(settings.getString("DRAWNUM","1"), TextView.BufferType.EDITABLE);
        Toast.makeText(this, "Restart State",
                Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences settings = getSharedPreferences("MYPREF", 0);
        drawNumBox.setText(settings.getString("DRAWNUM","1"), TextView.BufferType.EDITABLE);
        Toast.makeText(this, "Resume State",
                Toast.LENGTH_LONG).show();
    }

    public void back(View v) {
        Intent returnToMenu = new Intent(this, MainMenu.class);
        startActivity(returnToMenu);
    }


    public void shuffle_draw(View v) {
        CheckBox wReplaceBox = (CheckBox) findViewById(R.id.check_replace);
        boolean isReplace = wReplaceBox.isChecked();
        String drawNum = drawNumBox.getText().toString();
        if(numOfNames < 0 || drawNum.matches("")) {
            showEmptyError(v);
            return;
        }
        int drawNumInt = Integer.parseInt(drawNum);
        if(drawNumInt > numOfNames && (!isReplace)) {
            showError(drawNumBox);
            return;
        }

        String name;
        View view;
        EditText ename;
        int group_num = ll.getChildCount();
        for(int i = 0; i < group_num; i++) {
            view = ll.getChildAt(i);
            if(view instanceof EditText) {
                ename = (EditText) view;
                name = ename.getText().toString();
                if(!name.matches(""))
                    namesList.add(name);
            }
        }

        Intent drawintent = new Intent(this, Names.class);
        drawintent.putStringArrayListExtra("NAME_LIST", (ArrayList) namesList);
        drawintent.putExtra("DRAW_NUM", drawNumInt);
        drawintent.putExtra("CHECK_REPLACE", isReplace);
        startActivity(drawintent);

    }

    private void clearNames(ViewGroup group) {
        int group_num = group.getChildCount();
        int viewindex = 0;
        for(int i = 0; i < group_num; i++) {
            View view = group.getChildAt(viewindex);
            if(view instanceof EditText)
                group.removeView(view);
            else
                viewindex++;
        }
    }


    private void showError(EditText et) {
        Animation shake = AnimationUtils.loadAnimation(this,R.anim.shake);
        et.startAnimation(shake);
        et.setError("Draw Too Many");
    }

    private void showEmptyError(View v) {
        Toast.makeText(this, "You have empty values",
                Toast.LENGTH_LONG).show();
        Animation shake = AnimationUtils.loadAnimation(this,R.anim.shake);
        v.startAnimation(shake);
    }

}
