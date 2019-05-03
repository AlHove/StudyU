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

public class EditExam extends AppCompatActivity {

    EditText examName;
    Button save, back, delete;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_exam);

        examName = findViewById(R.id.examName);
        final TimePicker examTime = findViewById(R.id.examTime);
        examTime.setIs24HourView(true);
        final DatePicker examDate = findViewById(R.id.examDate);
        save = findViewById(R.id.saveButton);
        back = findViewById(R.id.back);
        delete = findViewById(R.id.delete);
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        examName.setText(name);


        save.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        int day = examDate.getDayOfMonth();
                                        int month = examDate.getMonth();
                                        int year = examDate.getYear();

                                        int hour = examTime.getHour();
                                        int minute = examTime.getMinute();
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
                                        String where = WorkList.ExamEntry.COLUMN_EXAM_NAME + "=?";

                                        String[] whereArgs = new String[] {String.valueOf(name)};

                                        //put the values from the screen (not doing and editing here) into the object
                                        values.put(WorkList.ExamEntry.COLUMN_EXAM_NAME, examName.getText().toString()); // Get the exam name
                                        values.put(WorkList.ExamEntry.COLUMN_EXAM_DATE, date); // Get the exam date
                                        values.put(WorkList.ExamEntry.COLUMN_EXAM_TIME, time); // Get the exam time

                                        db.update(WorkList.ExamEntry.TABLE_NAME,values,where,whereArgs);
                                        startActivity(new Intent(getApplicationContext(), ExamDBDisplay.class));
                                    }
                                });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ExamDBDisplay.class));
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WorkDBHelper myDbHelper = new WorkDBHelper(getApplicationContext());
                SQLiteDatabase db = myDbHelper.getWritableDatabase();
                String where = WorkList.ExamEntry.COLUMN_EXAM_NAME + "=?";
                String[] whereArgs = new String[] {String.valueOf(name)};

               db.delete(WorkList.ExamEntry.TABLE_NAME,where,whereArgs);
            }
        });

    }
}