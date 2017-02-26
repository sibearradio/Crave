package com.hacksmith.vmcculloch.crave;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Switch;

public class SettingsActivity extends AppCompatActivity {
    private Switch millSwitch;
    private Switch soundSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //get switches
        millSwitch = (Switch) findViewById(R.id.millSwitch);
        soundSwitch = (Switch) findViewById(R.id.soundSwitch);

        //save preferences
        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        Boolean restoredMill = prefs.getBoolean("millOn", false);
        Boolean restoredSound = prefs.getBoolean("soundOn",false);
        if(restoredMill!=false|restoredSound!=false){
            millSwitch.setChecked(restoredMill);
            soundSwitch.setChecked(restoredSound);
        }
    }
    @Override
    public void onBackPressed(){
        SharedPreferences.Editor editor = getPreferences(MODE_PRIVATE).edit();
        editor.putBoolean("millOn", millSwitch.isChecked());
        editor.putBoolean("soundOn",soundSwitch.isChecked());
        editor.commit();
        super.onBackPressed();
    }
}

