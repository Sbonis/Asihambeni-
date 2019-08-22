package com.example.asihambeni;

public class Schedule {
    private String riderID;
    private String date;
    private String destination;
    private String pickUpPoint;
    private String time;
    private String type;

    public Schedule(){

    }

    public Schedule(String riderID, String date, String destination, String pickUpPoint, String time, String type) {
        this.riderID = riderID;
        this.date = date;
        this.destination = destination;
        this.pickUpPoint = pickUpPoint;
        this.time = time;
        this.type = type;
    }

    public String getRiderID() {
        return riderID;
    }

    public String getDate() {
        return date;
    }

    public String getDestination() {
        return destination;
    }

    public String getPickUpPoint() {
        return pickUpPoint;
    }

    public String getTime() {
        return time;
    }
    public String getType() {
        return type;
    }
}
