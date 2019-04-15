package com.example.GonkDroids.StudyU;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/*
Helps the Exam zDatabase
 */
public class ExamDBHelper extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "exam.db";
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_EXAM_TABLE = "CREATE TABLE " + ExamList.ExamEntry.TABLE_NAME + " (" +
            ExamList.ExamEntry._ID + " INTEGER PRIMARY KEY" + COMMA_SEP +
            ExamList.ExamEntry.COLUMN_EXAM_NAME + TEXT_TYPE + COMMA_SEP +
            ExamList.ExamEntry.COLUMN_EXAM_DATE + TEXT_TYPE + COMMA_SEP +
            ExamList.ExamEntry.COLUMN_EXAM_TIME + TEXT_TYPE + " )";


    private static final String SQL_DELETE = // Delete the database sql
            "DROP TABLE IF EXISTS " + ExamList.ExamEntry.TABLE_NAME;

    public ExamDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    public ExamDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) { //Creates a person in the database
        db.execSQL(SQL_CREATE_EXAM_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE);
        onCreate(db);
    }
}

