package com.hacksmith.vmcculloch.crave;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity {
    String searchList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        Intent intent = getIntent();
        searchList = intent.getStringExtra("list");



        //SETS THE ACTUAL TEXT VIEW
        TextView shown = (TextView)findViewById(R.id.resultsText);
        shown.setText(searchList);
    }
}
