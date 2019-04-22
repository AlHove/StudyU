package com.example.GonkDroids.StudyU;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.CalendarView;

import static android.R.id.list;

public class CalenderDisplay extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calander_main);
        CalanderView calanderView = findViewById(R.id.calendar_view)
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //instead of array and preferences have the DB stuff here


        WorkDBHelper assignmentHelper = new WorkDBHelper(getApplicationContext());
        SQLiteDatabase dbAssign = assignmentHelper.getReadableDatabase();
        WorkDBHelper examHelper = new WorkDBHelper(getApplicationContext());
        SQLiteDatabase dbExam = examHelper.getReadableDatabase();


        String[] projectionExam = {
                WorkList.ExamEntry.COLUMN_EXAM_NAME,
                WorkList.ExamEntry.COLUMN_EXAM_DATE,
                WorkList.ExamEntry.COLUMN_EXAM_TIME
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

        //now going to call method to return cursor

        Cursor assignCursor = dbAssign.query(WorkList.AssignmentEntry.COLUMN_ASSIGNMENT_NAME, //table to query
                bindAssignment,
                null, //columns for where, Null will return all rows
                null, //values for where
                null, //Group By, null is no group by
                null, //Having, null says return all rows
                WorkList.AssignmentEntry.COLUMN_TIME + " ASC" //names in alpabetical order
        );
        Cursor examCursor = dbAssign.query(WorkList.ExamEntry.TABLE_NAME, //table to query
                bindExam,
                null, //columns for where, Null will return all rows
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
        SimpleCursorAdapter adapterExam = new SimpleCursorAdapter(getApplicationContext(), R.layout.row_item_exam, cursor, projection, to, 0);
        SimpleCursorAdapter adapterAssignment = new SimpleCursorAdapter(getApplicationContext(), R.layout.row_item_assignment, cursor, projection, to, 0);

        //set the adapter to the list
        final ListView listViewAssignment = findViewById(R.id.);
        listView.setAdapter(adapter);

        //set up for the empty non data messaged
        TextView emptyView = findViewById(android.R.id.empty);
        listView.setEmptyView(emptyView);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display_db, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.enterValues) {
            Intent intent = new Intent(getApplicationContext(), AddExam.class);
            startActivity(intent);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

}