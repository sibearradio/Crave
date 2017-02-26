package com.hacksmith.vmcculloch.crave;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class FoodActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

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
        startActivity(new Intent(this, ResultsActivity.class));
    }
}