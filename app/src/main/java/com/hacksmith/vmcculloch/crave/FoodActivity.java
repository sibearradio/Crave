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

                go();
            }
        });
    }

    private void go(){
        startActivity(new Intent(this, SettingsActivity.class));
    }
}