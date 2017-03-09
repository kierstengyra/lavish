package com.arvention.lavish.model;

/**
 * Created by amcan on 3/9/2017.
 */

public class Place {

    private final int placeID;
    private final double xCoordinate;
    private final double yCoordinate;
    private final String openingHours;

    public Place(int placeID,
                 double xCoordinate,
                 double yCoordinate,
                 String openingHours) {

        this.placeID = placeID;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.openingHours = openingHours;

    }

    public int getPlaceID() {
        return placeID;
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
