package com.hacksmith.vmcculloch.crave;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class ResultsActivity extends AppCompatActivity {
    private String searchList;
    private ArrayList<String> foodResults = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        Intent intent = getIntent();
        searchList = intent.getStringExtra("list");

        Krave krave = new Krave();
        krave.addItems();
      
        foodResults = krave.resultString(searchList);


//        //SEARCH CODE HERE
//        foodResults.add("sushi");
//        foodResults.add("fish and chips");
//        foodResults.add("lobster");

        //SETS THE ACTUAL TEXT VIEW
        String displayedString = "";
        for (int i =0; i<foodResults.size();i++){
            if(i==foodResults.size()-1){
                displayedString+=" - "+foodResults.get(i);
            }
            else{
                displayedString+=" - "+foodResults.get(i)+"\r\n";
            }

        }
        TextView shown = (TextView)findViewById(R.id.resultsText);
        shown.setText(displayedString);
    }
}
