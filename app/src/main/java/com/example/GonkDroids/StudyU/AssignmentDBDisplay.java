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
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
/*
Display Assignments
*/

public class AssignmentDBDisplay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assignment_main);
        //instead of array and preferences have the DB stuff here

        FloatingActionButton fab = findViewById(R.id.fabAssign);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), AddAssign.class));
            }
        });

        WorkDBHelper dbHelper = new WorkDBHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();


        String[] projection = {
                WorkList.AssignmentEntry.COLUMN_ASSIGNMENT_NAME,
                WorkList.AssignmentEntry.COLUMN_DATE,
                WorkList.AssignmentEntry.COLUMN_TIME,
        };

        String[] bind = {
                WorkList.AssignmentEntry._ID,
                WorkList.AssignmentEntry.COLUMN_ASSIGNMENT_NAME,
                WorkList.AssignmentEntry.COLUMN_DATE,
                WorkList.AssignmentEntry.COLUMN_TIME,
        };

        //now going to call method to return cursor

        Cursor cursor = db.query(WorkList.AssignmentEntry.TABLE_NAME, //table to query
                bind,
                null, //columns for where, Null will return all rows
                null, //values for where
                null, //Group By, null is no group by
                null, //Having, null says return all rows
                WorkList.AssignmentEntry.COLUMN_DATE + " ASC" //names in alpabetical order
        );


        //the list items from the layout, will find these in the row_item,
        //these are the 4 fields being displayed
        int[] to = new int[]{
                R.id.assignmentName,  R.id.assignmentDate, R.id.assignmentTime,
        };

        //create the adapter
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getApplicationContext(), R.layout.row_item_assignment, cursor, projection, to, 0);

        //set the adapter to the list
        final ListView listView = findViewById(R.id.assignmentList);
        listView.setAdapter(adapter);

        //set up for the empty non data messaged
        TextView emptyView = findViewById(android.R.id.empty);
        listView.setEmptyView(emptyView);

        //need to set the On Item Click Listener
  /*      listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Handle the on-click and display a toast, will do more work here later
                Cursor cursor = (Cursor) parent.getItemAtPosition(position);
        // type up
            }


        }); */

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.assignment_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //menu option to clear the entire database, really helpful for testing, remove before going to production
        if (id == R.id.viewExam) {
            startActivity(new Intent(getApplicationContext(), ExamDBDisplay.class));
            return true;
        }
        if (id == R.id.viewCalender) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
