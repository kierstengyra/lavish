package com.arvention.lavish.model;

/**
 * Created by amcan on 3/9/2017.
 */

public class Place {

    private final int placeID;
    private final String name;
    private final double xCoordinate;
    private final double yCoordinate;
    private final String openingHours;

    public Place(int placeID,
                 String name,
                 double xCoordinate,
                 double yCoordinate,
                 String openingHours) {

        this.placeID = placeID;
        this.name = name;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.openingHours = openingHours;

    }

    public int getPlaceID() {
        return placeID;
    }

    public String getName() {
        return name;
    }

    public double getxCoordinate() {
        return xCoordinate;
    }

    public double getyCoordinate() {
        return yCoordinate;
    }

    public String getOpeningHours() {
        return openingHours;
    }
}
