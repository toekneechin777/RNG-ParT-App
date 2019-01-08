package com.example.anthony.rngpart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class DrawNames extends AppCompatActivity {
    private Spinner numNames;
    public int namesID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.activity_draw_names);

        numNames = (Spinner)findViewById(R.id.num_names);
        ArrayAdapter<String> numNamesList = new ArrayAdapter<String>(this   ,android.R.layout.simple_spinner_item,
                                                                        getResources().getStringArray(R.array.draw_names_num_options));
        numNamesList.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        numNames.setAdapter(numNamesList);
        numNames.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(parent.getItemAtPosition(position).equals("Choose number of names")) {
                    // Do nothing
                } else {
                    Toast.makeText(parent.getContext(), "You chose " + parent.getItemAtPosition(position).toString() + " num of names",
                            Toast.LENGTH_SHORT).show();
                    int numOfNames = Integer.parseInt(parent.getItemAtPosition(position).toString());

                    LinearLayout ll = (LinearLayout) findViewById(R.id.ll_names);

                    for(int i = 0; i < numOfNames; i++) {
                        EditText newName = new EditText(getApplicationContext());
                        newName.setId(namesID);
                        ll.addView(newName);
                        namesID++;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void back(View v) {
        Intent returnToMenu = new Intent(this, MainMenu.class);
        startActivity(returnToMenu);
    }

}
