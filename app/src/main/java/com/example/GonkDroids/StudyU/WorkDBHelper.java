package com.example.GonkDroids.StudyU;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/*
Helps the Work DB
Alyssa Hove 4/9/19
 */

public class WorkDBHelper extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 5;
    private static final String DATABASE_NAME = "work.db";
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";

    private static final String SQL_CREATE_ASSIGNMENT = "CREATE TABLE " + WorkList.AssignmentEntry.TABLE_NAME + " (" +
            WorkList.AssignmentEntry._ID + " INTEGER PRIMARY KEY" + COMMA_SEP +
            WorkList.AssignmentEntry.COLUMN_ASSIGNMENT_NAME + TEXT_TYPE + COMMA_SEP +
            WorkList.AssignmentEntry.COLUMN_DATE + TEXT_TYPE + COMMA_SEP +
            WorkList.AssignmentEntry.COLUMN_TIME + TEXT_TYPE + " )";

    private static final String SQL_CREATE_EXAM_TABLE = "CREATE TABLE " + WorkList.ExamEntry.TABLE_NAME + " (" +
            WorkList.ExamEntry._ID + " INTEGER PRIMARY KEY" + COMMA_SEP +
            WorkList.ExamEntry.COLUMN_EXAM_NAME + TEXT_TYPE + COMMA_SEP +
            WorkList.ExamEntry.COLUMN_EXAM_DATE + TEXT_TYPE + COMMA_SEP +
            WorkList.ExamEntry.COLUMN_EXAM_TIME + TEXT_TYPE + " )";

    private static final String SQL_DELETE_TABLE_EXAM = "DROP TABLE IF EXISTS " + WorkList.ExamEntry.TABLE_NAME;
    private static final String SQL_DELETE_TABLE_ASSIGNMENT ="DROP TABLE IF EXISTS " + WorkList.AssignmentEntry.TABLE_NAME;

    public WorkDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    WorkDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) { //Creates the database
        db.execSQL(SQL_CREATE_ASSIGNMENT);
        db.execSQL(SQL_CREATE_EXAM_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_TABLE_ASSIGNMENT);
        db.execSQL(SQL_DELETE_TABLE_EXAM);
        onCreate(db);
    }

    public String getExamID(String name){
            SQLiteDatabase db = this.getWritableDatabase();
            String query = "SELECT " + WorkList.ExamEntry._ID + " FROM " + WorkList.ExamEntry.TABLE_NAME + " WHERE " + WorkList.ExamEntry.COLUMN_EXAM_NAME + " = '" + name + "'";
            Cursor data = db.rawQuery(query, null);
            return data.getString(data.getColumnIndex("_id"));
        }

    public String getAssignmentID(String name) { // gets Assignment ID by assignment name
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + WorkList.AssignmentEntry._ID + " FROM " + WorkList.AssignmentEntry.TABLE_NAME + " WHERE " + WorkList.AssignmentEntry.COLUMN_ASSIGNMENT_NAME + " = '" + name + "'";
        Cursor data = db.rawQuery(query, null);
        return data.getString(data.getColumnIndex("_id"));
    }

}
