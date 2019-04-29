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


/*
Add_Assign
 */
public class AddAssign extends AppCompatActivity {

    EditText assignName;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_assignment);
        Toolbar toolbar = findViewById(R.id.toolbar_exam);
        setSupportActionBar(toolbar);

        assignName = findViewById(R.id.assignmentName);
        final TimePicker assignTime = findViewById(R.id.assignmentTime);
        assignTime.setIs24HourView(true);

        final DatePicker assignDate = findViewById(R.id.assignmentDate);
        save = findViewById(R.id.saveButton);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int day = assignDate.getDayOfMonth();
                int month = assignDate.getMonth();
                int year =  assignDate.getYear();

                int hour = assignTime.getHour();
                int minute = assignTime.getMinute();
                String singleMinute;
                String time;

                String date = (month + 1) + "/" + day + "/" + year;
                if (minute < 10) {
                    singleMinute = "0" + minute;
                    time = hour + ":" + singleMinute;
                }
                else {
                    time = hour + ":" + minute;
                }
                //when the user clicks on save create instance of DbHelper
                WorkDBHelper myDbHelper = new WorkDBHelper(getApplicationContext());
                SQLiteDatabase db = myDbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();

                //put the values from the screen (not doing and editing here) into the object
                values.put(WorkList.AssignmentEntry.COLUMN_ASSIGNMENT_NAME, assignName.getText().toString()); // Get the exam name
                values.put(WorkList.AssignmentEntry.COLUMN_DATE, date); // Get the exam date
                values.put(WorkList.AssignmentEntry.COLUMN_TIME, time); // Get the exam time


                //insert the values into the database
                db.insert(
                        WorkList.AssignmentEntry.TABLE_NAME,  //table name for insert
                        null,  //null is all columns
                        values);  //values for the insert

                //clear the input text field
                assignName.setText("");
                Intent intent = new Intent(getApplicationContext(), AssignmentDBDisplay.class);
                startActivity(intent);
            }

        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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
        return super.onOptionsItemSelected(item);
    }
}