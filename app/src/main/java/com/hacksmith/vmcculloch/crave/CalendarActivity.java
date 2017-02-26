package com.hacksmith.vmcculloch.crave;

import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class CalendarActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);


    }

//    public void stuff(){
//        displayDate = (TextView) findViewById(R.id.dateView);
//
//
//        final Calendar c = Calendar.getInstance();
//        yy = c.get(Calendar.YEAR);
//        mm = c.get(Calendar.MONTH);
//        dd = c.get(Calendar.DAY_OF_MONTH);
//
//        // set current date into textview
//        displayDate.setText(new StringBuilder()
//                // Month is 0 based, just add 1
//                .append(yy).append(" ").append("-").append(mm + 1).append("-")
//                .append(dd));
//    }
}
