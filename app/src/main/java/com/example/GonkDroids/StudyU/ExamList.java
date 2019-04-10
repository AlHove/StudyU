package com.example.GonkDroids.StudyU;

import android.provider.BaseColumns;

public final class ExamList {

    public ExamList(){}

    public static abstract class ExamEntry implements BaseColumns{
        public static final String TABLE_NAME="Exam"; // Creates a Exam Table
        public static final String COLUMN_EXAM_NAME="examName"; // Creates examName String
        public static final String COLUMN_EXAM_DATE="Date"; // Creates Date String
        public static final String COLUMN_EXAM_TIME="Time"; // Creates Time string
    }

}