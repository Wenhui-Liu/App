package com.example.medicaltranslator.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MedicalBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "medical.db";

    public MedicalBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + MedicalDbSchema.MedicalTable.NAME + "(" +
                MedicalDbSchema.MedicalTable.Cols.ENG_NAME + ", " +
                MedicalDbSchema.MedicalTable.Cols.CN_NAME + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
