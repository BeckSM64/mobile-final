package com.example.shane_000.parkactivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_parks);

        //Initialize stuff
        parkListView = (ListView) findViewById(R.id.parkListView);
        arrayOfParks = new ArrayList<>();

        //Receive intent and extras from main activity
        Intent intent = getIntent();
        String value = intent.getStringExtra("key"); //if it's a string you stored.

        //Set tempText to value passed from main activity
        arrayOfParks.add(value);//Add park to the array
        arrayOfParks.add("Bryant Park");//Eventually get rid of this
        arrayOfParks.add("Park 1");
        arrayOfParks.add("Park 2");
        arrayOfParks.add("Park 3");

        //create adapter and test it and stuff with the list view
        //this should eventually pull from a SQLite database
        //using a CursorAdaptor
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, arrayOfParks);

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
            Intent myIntent = new Intent(ListParks.this, DisplayParkInfo.class);
            myIntent.putExtra("key", "HELLO, WORLD");//Send data here to new activity
            startActivity(myIntent);
        }
    };

}
