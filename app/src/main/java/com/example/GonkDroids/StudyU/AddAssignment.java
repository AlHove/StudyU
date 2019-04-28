package com.example.GonkDroids.StudyU;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;


/*
Add_Assignment
 */

public class AddAssignment extends AppCompatActivity {

    EditText assignmentName;
    Button save;
    Date chosenDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_assignment);
        Toolbar toolbar = findViewById(R.id.toolbar_assignment);
        setSupportActionBar(toolbar);
        //grab references to our input fields

        assignmentName = findViewById(R.id.assignmentName);
        final TimePicker assignmentTime = findViewById(R.id.assignmentTime);
        assignmentTime.setIs24HourView(true);
        final DatePicker assignmentDate = findViewById(R.id.assignmentDate);


        save = findViewById(R.id.saveButton);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int day = assignmentDate.getDayOfMonth();
                int month = assignmentDate.getMonth();
                int year =  assignmentDate.getYear();

                int hour = assignmentTime.getHour();
                int minute = assignmentTime.getMinute();

                String date = day + "/" + month + "/" + year;
                String time = hour + ":" + minute;

                //when the user clicks on save create instance of DbHelper
                WorkDBHelper myDbHelper = new WorkDBHelper(getApplicationContext());
                SQLiteDatabase db = myDbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();

                //put the values from the screen (not doing and editing here) into the object
                values.put(WorkList.AssignmentEntry.COLUMN_ASSIGNMENT_NAME, assignmentName.getText().toString()); // Get the person first name
                values.put(WorkList.AssignmentEntry.COLUMN_DATE, date); // Get the person last name
                values.put(WorkList.AssignmentEntry.COLUMN_TIME, time); // Get the person email


                //insert the values into the database
               db.insert(
                        WorkList.AssignmentEntry.TABLE_NAME,  //table name for insert
                        null,  //null is all columns
                        values);  //values for the insert

                //clear the input fields
                assignmentName.setText("");
                assignmentName.requestFocus();
            }

        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.addassignment_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //Start display assignment activity
        if (id == R.id.viewAssignment) {
            Intent intent = new Intent(getApplicationContext(), AssignmentDBDisplay.class);
            startActivity(intent);
            return true;
        }
        //menu option to clear the entire database, really helpful for testing, remove before going to production
        if (id == R.id.viewExam) {
            Intent intent = new Intent(getApplicationContext(), ExamDBDisplay.class);
            startActivity(intent);
            return true;
        }

        if (id == R.id.viewCalender) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
