package com.example.GonkDroids.StudyU;

import android.provider.BaseColumns;

public final class ExamList {

    public ExamList(){}

    static abstract class ExamEntry implements BaseColumns{
        static final String TABLE_NAME="Exam"; // Creates a Exam Table
        static final String COLUMN_EXAM_NAME="examName"; // Creates examName String
        static final String COLUMN_EXAM_DATE="Date"; // Creates Date String
        static final String COLUMN_EXAM_TIME="Time"; // Creates Time string
    }

}