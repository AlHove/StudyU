package com.example.GonkDroids.StudyU;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.telephony.PhoneNumberUtils;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.GonkDroids.StudyU.AssignmentDBHelper;
import com.example.GonkDroids.StudyU.AssignmentList;
/*
Add_Assignment
 */

public class AddAssignment extends AppCompatActivity {

    EditText assignmentName, assignmentDate, assignmentTime;
    Button save;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_assignment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //grab references to our input fields

        assignmentName = (EditText) findViewById(R.id.assignmentName);
        assignmentDate = (EditText) findViewById(R.id.assignmentDate);
        assignmentTime = (EditText) findViewById(R.id.assignmentTime);

        save = (Button) findViewById(R.id.saveButton);



        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //when the user clicks on save create instance of DbHelper
                AssignmentDBHelper myDbHelper = new AssignmentDBHelper(getApplicationContext());
                SQLiteDatabase db = myDbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();

                //put the values from the screen (not doing and editing here) into the object
                values.put(AssignmentList.AssignmentEntry.COLUMN_ASSIGNMENT_NAME, assignmentName.getText().toString()); // Get the person first name
                values.put(AssignmentList.AssignmentEntry.COLUMN_DATE, assignmentDate.getText().toString()); // Get the person last name
                values.put(AssignmentList.AssignmentEntry.COLUMN_TIME, assignmentTime.getText().toString()); // Get the person email


                //insert the values into the database
                long newRowId = db.insert(
                        AssignmentList.AssignmentEntry.TABLE_NAME,  //table name for insert
                        null,  //null is all columns
                        values);  //values for the insert

                //clear the input fields
                assignmentName.setText("");
                assignmentDate.setText("");
                assignmentTime.setText("");
                assignmentName.requestFocus();
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
            Intent intent = new Intent(getApplicationContext(), AssignmentDBDisplay.class);
            startActivity(intent);
            return true;
        }
        //menu option to clear the entire database, really helpful for testing, remove before going to production
        if (id == R.id.clearDatabase) {
            AssignmentDBHelper myDbHelper = new AssignmentDBHelper(getApplicationContext());
            SQLiteDatabase db = myDbHelper.getWritableDatabase();
            db.delete(AssignmentList.AssignmentEntry.TABLE_NAME,"1",null);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
