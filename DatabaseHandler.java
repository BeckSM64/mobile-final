package com.example.shane_000.parkactivities;

import android.app.ActionBar;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Shane Harrington on 12/30/2016.
 * Database for the parks
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static DatabaseHandler sInstance;

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "parksDB";
    private static final String TABLE_NAME = "parks";

    private Context sContext;

    //Column names in table
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_STATE = "state";
    private static final String KEY_ZIP = "zip";

    //array list of row ids
    private static ArrayList<Integer> rowIdArray = new ArrayList<>();

    public static synchronized DatabaseHandler getInstance(Context context) {

        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = new DatabaseHandler(context.getApplicationContext());
        }
        return sInstance;
    }

    private DatabaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //this.sContext = context;
    }

    //Create the table
    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_PARKS_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " AUTOINCREMENT INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_ADDRESS + " TEXT," + KEY_STATE + " TEXT,"
                + KEY_ZIP + " TEXT" + ")";
        db.execSQL(CREATE_PARKS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public long getProfilesCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        long cnt  = DatabaseUtils.queryNumEntries(db, TABLE_NAME);
        //int numRows = (int) DatabaseUtils.longForQuery(db, "SELECT COUNT(*) FROM table_name", null);
        db.close();
        return cnt;
        //return numRows;
    }

    void addPark(Park park){
        SQLiteDatabase db = this.getWritableDatabase();//Get the database

        //Creates a row to be added to table
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, park.getParkName());
        values.put(KEY_ADDRESS, park.getParkAddress());
        values.put(KEY_STATE, park.getParkState());
        values.put(KEY_ZIP, park.getParkZip());

        //Insert row into table
        db.insert(TABLE_NAME, null, values);

        //hopefully get id from last insert
        String query = "SELECT ROWID from " + TABLE_NAME + " order by ROWID DESC limit 1";
        Cursor cursor = db.rawQuery(query, null);

        //get the id from results
        if (cursor != null && cursor.moveToFirst()) {
            long lastId = cursor.getLong(0); //The 0 is the column index, we only have 1 column, so the index is 0
            int lastIdInt = (int) lastId;
            rowIdArray.add(lastIdInt);//Add this id to the array list of row ids
            Log.d("Add Park", "Successfully added park with row id" + lastId);
        }
        cursor.close();
    }

    Park getPark(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        //new cursor
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE ID = " + id + ";";
        Cursor cursor = db.rawQuery(query, null);

        //Cursor cursor = db.query(TABLE_NAME, new String[] {KEY_ID},null,null,null,null,null);
        Park park = null;

        if(cursor != null && cursor.moveToFirst()){
            //get park
            do{
                String rowId = cursor.getString(0);
                String rowName = cursor.getString(1);
                park = new Park(3, rowName, "aesdfa", "adsfa", "asdfa");//Test returning park
                //Log.d("YO", "1");
            }while(cursor.moveToNext());
        }
        cursor.close();
        return park;
    }

    void removePark(int index){
        SQLiteDatabase db = this.getReadableDatabase();
        //delete from table
        int rowId = rowIdArray.get(index);
        db.execSQL("delete from " + TABLE_NAME + " where id ='" + rowId + "'");
        rowIdArray.remove(index);
        Log.d("Add Park", "Successfully removed park with row id" + rowId);
        //find max row id value in database table

        //update autoincrement
        //db.execSQL("ALTER TABLE " + TABLE_NAME + " AUTO_INCREMENT " + "= " + id + 1);
    }

    void removeAll(){
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("delete from " + TABLE_NAME + " where id >'" + 0 + "'");
    }

    int getMaxId(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT MAX(id) AS max_id FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);

        int id = 0;
        if(cursor.moveToFirst()){
            do{
                id = cursor.getInt(0);
            } while(cursor.moveToNext());
        }
        cursor.close();
        return id;
    }
}
