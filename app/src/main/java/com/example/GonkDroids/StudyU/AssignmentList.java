package com.example.GonkDroids.StudyU;

import android.provider.BaseColumns;

public final class AssignmentList {

    public AssignmentList(){}

    public static abstract class AssignmentEntry implements BaseColumns{
        public static final String TABLE_NAME="Assignment"; // Creates an Assignment Table
        public static final String COLUMN_ASSIGNMENT_NAME="Assignment"; // Creates Assignment String
        public static final String COLUMN_DATE="Date"; // Creates Date string
        public static final String COLUMN_TIME="Time"; // Creates Email String
    }

}