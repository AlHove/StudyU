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
Add_Exam
 */
public class AddExam extends AppCompatActivity {

    EditText examName;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_exam);
        Toolbar toolbar = findViewById(R.id.toolbar_exam);
        setSupportActionBar(toolbar);
        //grab references to our input fields

        examName = findViewById(R.id.examName);
        final TimePicker examTime = findViewById(R.id.examTime);
        examTime.setIs24HourView(true);

        final DatePicker examDate = findViewById(R.id.examDate);
        save = findViewById(R.id.saveButton);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int day = examDate.getDayOfMonth();
                int month = examDate.getMonth();
                int year =  examDate.getYear();

                int hour = examTime.getHour();
                int minute = examTime.getMinute();
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
                values.put(WorkList.ExamEntry.COLUMN_EXAM_NAME, examName.getText().toString()); // Get the exam name
                values.put(WorkList.ExamEntry.COLUMN_EXAM_DATE, date); // Get the exam date
                values.put(WorkList.ExamEntry.COLUMN_EXAM_TIME, time); // Get the exam time


                //insert the values into the database
                 db.insert(
                        WorkList.ExamEntry.TABLE_NAME,  //table name for insert
                        null,  //null is all columns
                        values);  //values for the insert

                //clear the input text field
                examName.setText("");
                Intent intent = new Intent(getApplicationContext(), ExamDBDisplay.class);
                startActivity(intent);
            }

        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.addexam_menu, menu);
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