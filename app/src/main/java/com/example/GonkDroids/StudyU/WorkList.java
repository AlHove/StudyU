package com.example.GonkDroids.StudyU;

import android.provider.BaseColumns;

public final class WorkList {

    public WorkList(){}

    static abstract class ExamEntry implements BaseColumns{
        static final String TABLE_NAME="Exam"; // Creates a Exam Table
        static final String COLUMN_EXAM_NAME="examName"; // Creates examName String
        static final String COLUMN_EXAM_DATE="Date"; // Creates Date String
        static final String COLUMN_EXAM_TIME="Time"; // Creates Time string
    }

    static abstract class AssignmentEntry implements BaseColumns{
        static final String TABLE_NAME="Assignment"; // Creates an Assignment Table
        static final String COLUMN_ASSIGNMENT_NAME="assignmentName"; // Creates Assignment String
        static final String COLUMN_DATE="Date"; // Creates Date string
        static final String COLUMN_TIME="Time"; // creates time string
    }

}