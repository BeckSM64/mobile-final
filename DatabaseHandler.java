package com.example.shane_000.parkactivities;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by shane_000 on 12/30/2016.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "parksDB";
    private static final String TABLE_NAME = "parks";

    //Column names in table
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_STATE = "state";
    private static final String KEY_ZIP = "zip";

    public DatabaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Create the table
    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_PARKS_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_ADDRESS + " TEXT," + KEY_STATE + " TEXT,"
                + KEY_ZIP + " TEXT" + ")";
        db.execSQL(CREATE_PARKS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
