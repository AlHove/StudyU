package com.example.GonkDroids.StudyU;

//Created by Kaleb 05/03/19

import android.app.Activity;
import android.os.Bundle;

//Whenever notification is clicked it opens up the calendar main layout
public class NotificationActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calander_main);
    }
}
