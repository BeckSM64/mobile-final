package com.example.shane_000.parkactivities;

/**
 * Created by shane_000 on 12/30/2016.
 * Park Class
 */

public class Park {

    int id;
    String parkName;
    String parkAddress;
    String parkState;
    String parkZip;

    public Park(int id, String name, String address, String state, String zip){

        this.id = id;
        parkName = name;
        parkAddress = address;
        parkState = state;
        parkZip = zip;
    }

    //Getters and setters

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getParkName(){
        return parkName;
    }

    public void setParkName(String parkName){
        this.parkName = parkName;
    }

    public String getParkAddress(){
        return parkAddress;
    }

    public void setParkAddress(String parkAddress){
        this.parkAddress = parkAddress;
    }

    public String getParkState(){
        return parkState;
    }

    public void setParkState(String parkState){
        this.parkState = parkState;
    }

    public String getParkZip(){
        return parkZip;
    }

    public void setParkZip(String parkZip){
        this.parkZip = parkZip;
    }
}
