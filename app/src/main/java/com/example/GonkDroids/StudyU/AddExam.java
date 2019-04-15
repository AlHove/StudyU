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

import com.example.GonkDroids.StudyU.AssignmentDBHelper;
import com.example.GonkDroids.StudyU.AssignmentList;
import com.example.GonkDroids.StudyU.ExamDBDisplay;
import com.example.GonkDroids.StudyU.ExamDBHelper;
import com.example.GonkDroids.StudyU.ExamList;
/*
Add_Assignment
 */

public class AddExam extends AppCompatActivity {

    EditText assignmentName, lastName, email, phone;
    Button save;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_exam);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //grab references to our input fields

        Assignment = (EditText) findViewById(R.id.assignmentName);
        lastName = (EditText) findViewById(R.id.lastName);
        email = (EditText) findViewById(R.id.email);
        phone = (EditText) findViewById(R.id.phone);

        // format the phone number for the user
        phone.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
        save = (Button) findViewById(R.id.saveButton);



        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //when the user clicks on save create instance of DbHelper
                AssignmentDBHelper myDbHelper = new AssignmentDBHelper(getApplicationContext());
                SQLiteDatabase db = myDbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();

                //put the values from the screen (not doing and editing here) into the object
                values.put(AssignmentList.AssignmentEntry.COLUMN_ASSIGNMENT_NAME, firstName.getText().toString()); // Get the person first name
                values.put(AssignmentList.AssignmentEntry.COLUMN_DATE, lastName.getText().toString()); // Get the person last name
                values.put(AssignmentList.AssignmentEntry.COLUMN_TIME, email.getText().toString()); // Get the person email
                values.put(AssignmentList.AssignmentEntry.COLUMN_PHONE, PhoneNumberUtils.formatNumber(phone.getText().toString()));

                //insert the values into the database
                long newRowId = db.insert(
                        AssignmentList.AssignmentEntry.TABLE_NAME,  //table name for insert
                        null,  //null is all columns
                        values);  //values for the insert

                //clear the input fields
                .setText("");
                lastName.setText("");
                email.setText("");
                phone.setText("");
                firstName.requestFocus();
            }

        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_user, menu);
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
            ExamDBHelper myDbHelper = new ExamDBHelper(getApplicationContext());
            SQLiteDatabase db = myDbHelper.getWritableDatabase();
            db.delete(ExamList.ExamEntry.TABLE_NAME,"1",null);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}