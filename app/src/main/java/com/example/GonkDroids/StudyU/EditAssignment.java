package com.example.GonkDroids.StudyU;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

/*
Alyssa Hove
Used to edit assignments in the database
 */


public class EditAssignment extends AppCompatActivity {

    EditText assignName;
    Button save, back, delete;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_assginment);

        assignName = findViewById(R.id.assignmentName);
        final TimePicker assignTime = findViewById(R.id.assignmentTime);
        assignTime.setIs24HourView(true);
        final DatePicker assignDate = findViewById(R.id.assignmentDate);
        save = findViewById(R.id.saveButton);
        back = findViewById(R.id.back);
        delete = findViewById(R.id.delete);
        final Intent intent = getIntent();
        id = intent.getStringExtra("id");


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int day = assignDate.getDayOfMonth();
                int month = assignDate.getMonth();
                int year = assignDate.getYear();

                int hour = assignTime.getHour();
                int minute = assignTime.getMinute();
                String singleMinute;
                String time;

                String date = (month + 1) + "/" + day + "/" + year;
                if (minute < 10) {
                    singleMinute = "0" + minute;
                    time = hour + ":" + singleMinute;
                } else {
                    time = hour + ":" + minute;
                }
                //when the user clicks on save create instance of DbHelper
                WorkDBHelper myDbHelper = new WorkDBHelper(getApplicationContext());
                SQLiteDatabase db = myDbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                String where = WorkList.AssignmentEntry._id + "=?";

                String[] whereArgs = new String[] {id};

                //put the values from the screen (not doing and editing here) into the object
                values.put(WorkList.AssignmentEntry.COLUMN_ASSIGNMENT_NAME, assignName.getText().toString()); // Get the exam name
                values.put(WorkList.AssignmentEntry.COLUMN_DATE, date); // Get the exam date
                values.put(WorkList.AssignmentEntry.COLUMN_TIME, time); // Get the exam time

                db.update(WorkList.AssignmentEntry.TABLE_NAME,values,where,whereArgs);
                startActivity(new Intent(getApplicationContext(), AssignmentDBDisplay.class));
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AssignmentDBDisplay.class));
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WorkDBHelper myDbHelper = new WorkDBHelper(getApplicationContext());
                SQLiteDatabase db = myDbHelper.getWritableDatabase();
                String where = WorkList.AssignmentEntry._id + "=?";
                String[] whereArgs = new String[] {id};

                db.delete(WorkList.AssignmentEntry.TABLE_NAME,where,whereArgs);
                startActivity(new Intent(getApplicationContext(), AssignmentDBDisplay.class));
            }
        });
    }
}