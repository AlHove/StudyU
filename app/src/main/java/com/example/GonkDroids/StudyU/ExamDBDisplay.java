package com.example.GonkDroids.StudyU;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
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

import java.util.List;

import static android.R.id.list;

public class ExamDBDisplay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exam_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //instead of array and preferences have the DB stuff here

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ExamDBHelper.class);
                startActivity(intent);
            }
        });

        ExamDBHelper dbHelper = new ExamDBHelper(getApplicationContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        //
        //out of dataset which columns to use projection

        String[] projection = {
                ExamList.ExamEntry.COLUMN_EXAM_NAME,
                ExamList.ExamEntry.COLUMN_EXAM_DATE,
                ExamList.ExamEntry.COLUMN_EXAM_TIME
        };

        String[] bind = {
                ExamList.ExamEntry._ID,
                ExamList.ExamEntry.COLUMN_EXAM_NAME,
                ExamList.ExamEntry.COLUMN_EXAM_DATE,
                ExamList.ExamEntry.COLUMN_EXAM_TIME
        };

        //now going to call method to return cursor

        Cursor cursor = db.query(ExamList.ExamEntry.TABLE_NAME, //table to query
                bind,
                null, //columns for where, Null will return all rows
                null, //values for where
                null, //Group By, null is no group by
                null, //Having, null says return all rows
                ExamList.ExamEntry.COLUMN_EXAM_DATE + " ASC" //names in alpabetical order
        );


        //the list items from the layout, will find these in the row_item,
        //these are the 4 fields being displayed
        int[] to = new int[]{
                R.id.examName,  R.id.examDate, R.id.examTime
        };

        //create the adapter
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getApplicationContext(), R.layout.row_item_exam, cursor, projection, to, 0);

        //set the adapter to the list
        final ListView listView = (ListView) findViewById(list);
        listView.setAdapter(adapter);

        //set up for the empty non data messaged
        TextView emptyView = (TextView) findViewById(android.R.id.empty);
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