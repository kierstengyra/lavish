package com.arvention.lavish.model;

/**
 * Created by amcan on 3/9/2017.
 */

public class Toilet {

    private static int nextId = 0;

    private final int toiletID;
    private final String name;
    private final double xCoordinate;
    private final double yCoordinate;
    private final boolean hasBidet;
    private final boolean hasFlush;
    private final boolean hasSoap;
    private final boolean isFree;
    private final boolean isPWDFriendly;
    //private final int cubicleCount
    private final String openingHours;

    public Toilet(String name,
                  double xCoordinate,
                  double yCoordinate,
                  boolean hasBidet,
                  boolean hasFlush,
                  boolean hasSoap,
                  boolean isFree,
                  boolean isPWDFriendly,
                  String openingHours) {

        this.toiletID = nextId;
        this.name = name;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.hasBidet = hasBidet;
        this.hasFlush = hasFlush;
        this.hasSoap = hasSoap;
        this.isFree = isFree;
        this.isPWDFriendly = isPWDFriendly;
        this.openingHours = openingHours;
        nextId++;
    }

    public int getToiletID() {
        return toiletID;
    }

    public String getName() { return name; }

    public double getxCoordinate() {
        return xCoordinate;
    }

    public double getyCoordinate() {
        return yCoordinate;
    }

    public boolean isHasBidet() {
        return hasBidet;
    }

    public boolean isHasFlush() {
        return hasFlush;
    }

    public boolean isHasSoap() {
        return hasSoap;
    }

    public boolean isFree() {
        return isFree;
    }

    public boolean isPWDFriendly() {
        return isPWDFriendly;
    }

    /*
    public int getCubicleCount() {
        return cubicleCount;
    }*/

    public String getOpeningHours() { return openingHours; }

}
