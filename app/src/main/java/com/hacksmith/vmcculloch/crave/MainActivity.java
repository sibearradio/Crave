package com.hacksmith.vmcculloch.crave;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.SoundPool;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {
    private EditText eText;
    private static SoundPool sSoundPool;
    private int ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        eText = (EditText) findViewById(R.id.playerpassword) ;
        eText.setTypeface(Typeface.DEFAULT);


        ((Button) findViewById(R.id.loginButton)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                go();
            }
        });
    }



    private void go(){
        startActivity(new Intent(this, FoodActivity.class));
    }
//    private void init(){
//        sSoundPool = new SoundPool.Builder().build();
//        ID = sSoundPool.load(getContext(), R.raw.small, 0);
//    }
}