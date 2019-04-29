package com.example.GonkDroids.StudyU;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.*;
import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private String CurrentDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calander_main);

        final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c = Calendar.getInstance();
        CurrentDate = sdf.format(c.getTime());
        final CalendarView cv = findViewById(R.id.calendar_view); // get the reference of CalendarView

        Toolbar toolbar = findViewById(R.id.calendar_toolbar);
        setSupportActionBar(toolbar);

        cv.setOnDateChangeListener(new OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
               CurrentDate = sdf.format(new Date(cv.getDate()));
                String whichDayAssignment = WorkList.AssignmentEntry.COLUMN_DATE + " = " + CurrentDate;
                String whichDayExam = WorkList.ExamEntry.COLUMN_EXAM_DATE + " = " + CurrentDate;
                refreshDB(whichDayExam,whichDayAssignment);
            }
        });
        //instead of array and preferences have the DB stuff here

        WorkDBHelper WorkHelper = new WorkDBHelper(getApplicationContext());
        SQLiteDatabase db = WorkHelper.getReadableDatabase();


        String[] projectionExam = {
                WorkList.ExamEntry.COLUMN_EXAM_NAME,
                WorkList.ExamEntry.COLUMN_EXAM_DATE,
                WorkList.ExamEntry.COLUMN_EXAM_TIME,
        };

        String[] projectionAssign = {
                WorkList.AssignmentEntry.COLUMN_ASSIGNMENT_NAME,
                WorkList.AssignmentEntry.COLUMN_DATE,
                WorkList.AssignmentEntry.COLUMN_TIME,
        };

        String[] bindExam = {
                WorkList.ExamEntry._ID,
                WorkList.ExamEntry.COLUMN_EXAM_NAME,
                WorkList.ExamEntry.COLUMN_EXAM_DATE,
                WorkList.ExamEntry.COLUMN_EXAM_TIME,
        };

        String[] bindAssignment = {
                WorkList.AssignmentEntry._ID,
                WorkList.AssignmentEntry.COLUMN_ASSIGNMENT_NAME,
                WorkList.AssignmentEntry.COLUMN_DATE,
                WorkList.AssignmentEntry.COLUMN_TIME,
        };

        String whichDayAssignment = WorkList.AssignmentEntry.COLUMN_DATE + " = " + CurrentDate;
        String whichDayExam = WorkList.ExamEntry.COLUMN_EXAM_DATE + " = " + CurrentDate;



        Cursor assignCursor = db.query(WorkList.AssignmentEntry.TABLE_NAME, //table to query
                bindAssignment,
                whichDayAssignment, //columns for where, Null will return all rows
                null, //values for where
                null, //Group By, null is no group by
                null, //Having, null says return all rows
                WorkList.AssignmentEntry.COLUMN_TIME + " ASC" //time order
        );

        Cursor examCursor = db.query(WorkList.ExamEntry.TABLE_NAME, //table to query
                bindExam,
                whichDayExam, //columns for where, Null will return all rows
                null, //values for where
                null, //Group By, null is no group by
                null, //Having, null says return all rows
                WorkList.ExamEntry.COLUMN_EXAM_TIME + " ASC" //times in order
        );

        //the list items from the layout, will find these in the row_item,
        //these are the 4 fields being displayed
        int[] to = new int[]{
                R.id.examName, R.id.examDate, R.id.examTime, R.id.assignmentName, R.id.assignmentDate, R.id.assignmentTime
        };

        //create the adapters
        SimpleCursorAdapter adapterExam = new SimpleCursorAdapter(getApplicationContext(), R.layout.row_item_exam, examCursor, projectionExam, to, 0);
        SimpleCursorAdapter adapterAssignment = new SimpleCursorAdapter(getApplicationContext(), R.layout.row_item_assignment, assignCursor, projectionAssign, to, 0);

        //set the adapter to the list
        final ListView listViewAssignment = findViewById(R.id.assignments);
        listViewAssignment.setAdapter(adapterAssignment);


        //set the adapter to the list
        final ListView listViewExam = findViewById(R.id.exams);
        listViewExam.setAdapter(adapterExam);
    }

    public void refreshDB(String Exam, String Assignment){
        WorkDBHelper WorkHelper = new WorkDBHelper(getApplicationContext());
        SQLiteDatabase db = WorkHelper.getReadableDatabase();


        String[] projectionExam = {
                WorkList.ExamEntry.COLUMN_EXAM_NAME,
                WorkList.ExamEntry.COLUMN_EXAM_DATE,
                WorkList.ExamEntry.COLUMN_EXAM_TIME,
        };

        String[] projectionAssign = {
                WorkList.AssignmentEntry.COLUMN_ASSIGNMENT_NAME,
                WorkList.AssignmentEntry.COLUMN_DATE,
                WorkList.AssignmentEntry.COLUMN_TIME,
        };

        String[] bindExam = {
                WorkList.ExamEntry._ID,
                WorkList.ExamEntry.COLUMN_EXAM_NAME,
                WorkList.ExamEntry.COLUMN_EXAM_DATE,
                WorkList.ExamEntry.COLUMN_EXAM_TIME,
        };

        String[] bindAssignment = {
                WorkList.AssignmentEntry._ID,
                WorkList.AssignmentEntry.COLUMN_ASSIGNMENT_NAME,
                WorkList.AssignmentEntry.COLUMN_DATE,
                WorkList.AssignmentEntry.COLUMN_TIME,
        };


        Cursor assignCursor = db.query(WorkList.AssignmentEntry.TABLE_NAME, //table to query
                bindAssignment,
                Assignment, //columns for where, Null will return all rows
                null, //values for where
                null, //Group By, null is no group by
                null, //Having, null says return all rows
                WorkList.AssignmentEntry.COLUMN_TIME + " ASC" //time order
        );

        Cursor examCursor = db.query(WorkList.ExamEntry.TABLE_NAME, //table to query
                bindExam,
                Exam, //columns for where, Null will return all rows
                null, //values for where
                null, //Group By, null is no group by
                null, //Having, null says return all rows
                WorkList.ExamEntry.COLUMN_EXAM_TIME + " ASC" //times in order
        );

        //the list items from the layout, will find these in the row_item,
        //these are the 4 fields being displayed
        int[] to = new int[]{
                R.id.examName, R.id.examDate, R.id.examTime, R.id.assignmentName, R.id.assignmentDate, R.id.assignmentTime
        };

        //create the adapters
        SimpleCursorAdapter adapterExam = new SimpleCursorAdapter(getApplicationContext(), R.layout.row_item_exam, examCursor, projectionExam, to, 0);
        SimpleCursorAdapter adapterAssignment = new SimpleCursorAdapter(getApplicationContext(), R.layout.row_item_assignment, assignCursor, projectionAssign, to, 0);

        //set the adapter to the list
        final ListView listViewAssignment = findViewById(R.id.assignments);
        listViewAssignment.setAdapter(adapterAssignment);


        //set the adapter to the list
        final ListView listViewExam = findViewById(R.id.exams);
        listViewExam.setAdapter(adapterExam);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu
        getMenuInflater().inflate(R.menu.calander_menu, menu);
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
            startActivity(new Intent(getApplicationContext(), AssignmentDBDisplay.class));
            return true;
        }
        //move to view exam
        if (id == R.id.viewExam) {
            startActivity(new Intent(getApplicationContext(), ExamDBDisplay.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

