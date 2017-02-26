package com.hacksmith.vmcculloch.crave;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FoodActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        ((Button) findViewById(R.id.settingsButton)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                goSettings();
            }
        });
        ((Button) findViewById(R.id.profilebutton)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                goProfile();
            }
        });
        ((Button) findViewById(R.id.calendarbutton)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                goCalendar();
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
}