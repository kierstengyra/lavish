package com.arvention.lavish.activity;

import com.arvention.lavish.model.Toilet;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.SphericalUtil;

import java.util.List;
import java.util.ListIterator;

/**
 * Created by NeilJustin on 3/9/2017.
 */

public abstract class MapDetailUtil {

    public static double computeRouteDistance(List<LatLng> route){
        ListIterator<LatLng> routeIterator = route.listIterator();
        double totalDistance = 0;
        LatLng curRoute, prevRoute = null;
        while(routeIterator.hasNext()){
            curRoute = routeIterator.next();
            if(prevRoute != null)
                totalDistance += SphericalUtil.computeDistanceBetween(prevRoute,curRoute);
            prevRoute = curRoute;
        }

        return totalDistance;
    }

    public static Toilet getNearestToilet(LatLng deviceLocation, List<Toilet> toilets){
        ListIterator<Toilet> toiletListIterator = toilets.listIterator();
        Toilet nearest = null;
        double shortestDistance = 99999999;
        while(toiletListIterator.hasNext()){
            Toilet current = toiletListIterator.next();
            LatLng currentLoc = new LatLng(current.getxCoordinate(),current.getyCoordinate());
            double distance = SphericalUtil.computeDistanceBetween(deviceLocation,currentLoc);
            if(nearest == null) {
                nearest = current;
                shortestDistance = distance;
            }
            else{
                if(distance<shortestDistance){
                    shortestDistance = distance;
                    nearest = current;
                }
            }
        }
        return nearest;
    }

}
