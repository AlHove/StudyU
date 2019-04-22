package com.example.GonkDroids.StudyU;

import android.annotation.SuppressLint;
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
import android.widget.EditText;


/*
Add_Assignment
 */

@SuppressLint("Registered")
public class AddExam extends AppCompatActivity {

    EditText examName, examDate, examTime;
    Button save;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_exam);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //grab references to our input fields

        examName = findViewById(R.id.examName);
        examDate = findViewById(R.id.examDate);
        examTime = findViewById(R.id.examTime);

        // format the phone number for the user
        save = findViewById(R.id.saveButton);



        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //when the user clicks on save create instance of DbHelper
                WorkDBHelper myDbHelper = new WorkDBHelper(getApplicationContext());
                SQLiteDatabase db = myDbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();

                //put the values from the screen (not doing and editing here) into the object
                values.put(WorkList.ExamEntry.COLUMN_EXAM_NAME, examName.getText().toString()); // Get the exam name
                values.put(WorkList.ExamEntry.COLUMN_EXAM_DATE, examDate.getText().toString()); // Get the exam date
                values.put(WorkList.ExamEntry.COLUMN_EXAM_TIME, examTime.getText().toString()); // Get the person email


                //insert the values into the database
                long newRowId = db.insert(
                        AssignmentList.AssignmentEntry.TABLE_NAME,  //table name for insert
                        null,  //null is all columns
                        values);  //values for the insert

                //clear the input fields
                examName.setText("");
                examDate.setText("");
                examTime.setText("");
            }

        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_assignment, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //Start display activity
        if (id == R.id.display) {
            Intent intent = new Intent(getApplicationContext(), ExamDBDisplay.class);
            startActivity(intent);
            return true;
        }
        //menu option to clear the entire database, really helpful for testing, remove before going to production
        if (id == R.id.clearDatabase) {
            WorkDBHelper myDbHelper = new WorkDBHelper(getApplicationContext());
            SQLiteDatabase db = myDbHelper.getWritableDatabase();
            db.delete(WorkList.ExamEntry.TABLE_NAME,"1",null);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}