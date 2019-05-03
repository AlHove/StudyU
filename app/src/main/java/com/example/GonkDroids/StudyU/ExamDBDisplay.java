package com.example.GonkDroids.StudyU;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
/*
Display Exams
 */

public class ExamDBDisplay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exam_main);
        Toolbar toolbar = findViewById(R.id.toolbar_exam);
        setSupportActionBar(toolbar);
        //instead of array and preferences have the DB stuff here

        FloatingActionButton fab = findViewById(R.id.fabExam);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddExam.class);
                startActivity(intent);
            }
        });

        WorkDBHelper dbHelper = new WorkDBHelper(getApplicationContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();


        String[] projection = {
                WorkList.ExamEntry.COLUMN_EXAM_NAME,
                WorkList.ExamEntry.COLUMN_EXAM_DATE,
                WorkList.ExamEntry.COLUMN_EXAM_TIME
        };

        String[] bind = {
                WorkList.ExamEntry._ID,
                WorkList.ExamEntry.COLUMN_EXAM_NAME,
                WorkList.ExamEntry.COLUMN_EXAM_DATE,
                WorkList.ExamEntry.COLUMN_EXAM_TIME
        };

        //now going to call method to return cursor

        Cursor cursor = db.query(WorkList.ExamEntry.TABLE_NAME, //table to query
                bind,
                null, //columns for where, Null will return all rows
                null, //values for where
                null, //Group By, null is no group by
                null, //Having, null says return all rows
                WorkList.ExamEntry.COLUMN_EXAM_DATE + " ASC" //names in alpabetical order
        );


        //the list items from the layout, will find these in the row_item,
        //these are the 4 fields being displayed
        int[] to = new int[]{
                R.id.examName,  R.id.examDate, R.id.examTime
        };

        //create the adapter
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getApplicationContext(), R.layout.row_item_exam, cursor, projection, to, 0);

        //set the adapter to the list
        final ListView listView = findViewById(R.id.examList);
        listView.setAdapter(adapter);

        //set up for the empty non data messaged
        TextView emptyView = findViewById(android.R.id.empty);
        listView.setEmptyView(emptyView);

        //need to set the On Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }


        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.exam_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.viewCalender) { // view calander
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            return true;
        }
        if (id == R.id.viewAssignment) {
            startActivity(new Intent(getApplicationContext(), AssignmentDBDisplay.class));
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