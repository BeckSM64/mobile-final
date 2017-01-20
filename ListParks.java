package com.example.shane_000.parkactivities;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Shane Harrington on 12/20/2016.
 * Second Activity for Park app
 */

public class ListParks extends AppCompatActivity{

    ListView parkListView;
    ArrayList<String> arrayOfParks;
    DatabaseHandler db;
    ArrayAdapter<String> adapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_parks);

        //Initialize stuff
        parkListView = (ListView) findViewById(R.id.parkListView);
        arrayOfParks = new ArrayList<>();

        //Database
        db = DatabaseHandler.getInstance(this);//Get an existing instance of the database

        //Get park from database
        long parkCount = db.getProfilesCount();
        int parkCountInt = (int) parkCount;
        Log.d("Current Park Count", Integer.toString(parkCountInt));

        //Populate list view
        for(int i = 1; i < parkCount + 1; i++){
            Park tempPark = db.getPark(i);
            arrayOfParks.add(tempPark.getParkName());
        }

        //db.removeAll();

        //create adapter and test it and stuff with the list view
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayOfParks);

        //set the adapter
        parkListView.setAdapter(adapter);

        //set the listener for the list view
        parkListView.setOnItemClickListener(itemClickListener);
    }

    //This should open a new activity when an item in the list view is clicked
    AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener(){

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id){
            //Create intent to load another activity
            //Intent myIntent = new Intent(ListParks.this, DisplayParkInfo.class);
            //myIntent.putExtra("key", "HELLO, WORLD");//Send data here to new activity
            //startActivity(myIntent);
            Log.d("position", Integer.toString(position));
            db.removePark(position);
            arrayOfParks.remove(position);
            adapter.notifyDataSetChanged();
        }
    };
}
