package com.example.shane_000.parkactivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Shane Harrington on 12/29/2016.
 * Main Activity for Park app
 */

public class MainActivity extends AppCompatActivity{

    //Widgets
    Button submitButton;
    EditText nameText;
    EditText addressText;
    EditText stateText;
    EditText zipText;

    //Park data
    String parkName;
    String parkAddress;
    String parkState;
    String parkZip;
    String tempText;

    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //CREATE THE DATABASE
        db = DatabaseHandler.getInstance(this);//This should create one instance of the database to use

        //Initialize widgets for this activity
        submitButton = (Button) findViewById(R.id.submitButton);
        nameText = (EditText) findViewById(R.id.nameText);
        addressText = (EditText) findViewById(R.id.addressText);
        stateText = (EditText) findViewById(R.id.stateText);
        zipText = (EditText) findViewById(R.id.zipText);

        //Set listeners
        submitButton.setOnClickListener(submitListener);

    }
    //REMEMBER TO GET RID OF THIS PLEASE THANKS
    //.setText("", TextView.BufferType.EDITABLE);

    public View.OnClickListener submitListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            parkName = nameText.getText().toString();
            parkAddress = addressText.getText().toString();
            parkState = stateText.getText().toString();
            parkZip = zipText.getText().toString();

            //just for testing purposes, pass this string of nonsense to load the next activity
            //and test exchanging data between activities
            tempText = parkName;// + ", " + parkAddress + ", " + parkState + ", " + parkZip;

            //Create intent to load another activity
            Intent myIntent = new Intent(MainActivity.this, ListParks.class);
            myIntent.putExtra("key", tempText);//Send data here to new activity
            MainActivity.this.startActivity(myIntent);

            //Add to database
            db.addPark(new Park(0, parkName, parkAddress, parkState, parkZip));
        }
    };
}
