package com.arvention.lavish.model;

/**
 * Created by amcan on 3/9/2017.
 */

public class Toilet {

    private final int toiletID;
    private final int placeID;
    private final double xCoordinate;
    private final double yCoordinate;
    private final boolean hasBidet;
    private final boolean hasFlush;
    private final boolean hasSoap;
    private final boolean isFree;
    private final boolean isPWDFriendly;
    private final int cubicleCount;

    public Toilet(int toiletID,
                  int placeID,
                  double xCoordinate,
                  double yCoordinate,
                  boolean hasBidet,
                  boolean hasFlush,
                  boolean hasSoap,
                  boolean isFree,
                  boolean isPWDFriendly,
                  int cubicleCount) {

        this.toiletID = toiletID;
        this.placeID = placeID;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.hasBidet = hasBidet;
        this.hasFlush = hasFlush;
        this.hasSoap = hasSoap;
        this.isFree = isFree;
        this.isPWDFriendly = isPWDFriendly;
        this.cubicleCount = cubicleCount;

    }

    public int getToiletID() {
        return toiletID;
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

    public int getCubicleCount() {
        return cubicleCount;
    }

}
