package com.hacksmith.vmcculloch.crave;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ToggleButton;

public class ProfileActivity extends AppCompatActivity {
    private EditText editText;
    private String editString;
    private ToggleButton underAge;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        editText = (EditText) findViewById(R.id.editText);
        underAge = (ToggleButton) findViewById(R.id.ageToggle);


        //save on backspace
        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        String restoredText = prefs.getString("text", null);
        Boolean restoredTruth = prefs.getBoolean("on/off",false);
        if(!TextUtils.isEmpty(restoredText)|restoredTruth!=false){
            editText.setText(restoredText);
            underAge.setChecked(restoredTruth);
        }



    }
    @Override
    public void onBackPressed(){
        SharedPreferences.Editor editor = getPreferences(MODE_PRIVATE).edit();
        editor.putString("text", editText.getText().toString());
        editor.putBoolean("on/off",underAge.isChecked());
        editor.commit();
        super.onBackPressed();
    }


}
