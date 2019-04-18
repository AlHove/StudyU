package com.example.GonkDroids.StudyU;

import android.provider.BaseColumns;

public final class AssignmentList {

    public AssignmentList(){}

     static abstract class AssignmentEntry implements BaseColumns{
        static final String TABLE_NAME="Assignment"; // Creates an Assignment Table
        static final String COLUMN_ASSIGNMENT_NAME="Assignment"; // Creates Assignment String
        static final String COLUMN_DATE="Date"; // Creates Date string
        static final String COLUMN_TIME="Time"; // Creates Email String
    }

}