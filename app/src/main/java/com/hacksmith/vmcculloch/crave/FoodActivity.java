package com.hacksmith.vmcculloch.crave;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class FoodActivity extends AppCompatActivity {
    private EditText searchText ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        searchText = (EditText) findViewById(R.id.search);




        ((ImageButton) findViewById(R.id.settingsButton)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                goSettings();
            }
        });
        ((ImageButton) findViewById(R.id.profilebutton)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                goProfile();
            }
        });
        ((ImageButton) findViewById(R.id.calendarbutton)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                goCalendar();
            }
        });
        ((Button) findViewById(R.id.go)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                goResults();
            }
        });
        ((ImageButton) findViewById(R.id.randombutton)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                goResults();
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }



    private void goSettings(){
        startActivity(new Intent(this, SettingsActivity.class));
    }
    private void goProfile(){
        startActivity(new Intent(this, ProfileActivity.class));
    }
    private void goCalendar(){
        startActivity(new Intent(this, CalendarActivity.class));
    }
    private void goResults(){
        EditText field = (EditText) findViewById(R.id.search);
        String searchString = field.getText().toString();
        Intent newIntent = new Intent(this,ResultsActivity.class);
        newIntent.putExtra("list",searchString);
        startActivity(newIntent);
    }
}