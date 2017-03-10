package com.arvention.lavish.activity;

import com.arvention.lavish.model.Toilet;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.SphericalUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

    /**
     * computes for travel time of the route
     * @param route the JSONObject of the route obtained from google api
     * @return returns the total travel time of the route in seconds
     */
    public static int computeTravelTime(JSONObject route){
        int totalTravelTime = 0;
        try {
            JSONArray legs = (JSONArray) route.get("legs");
            for(int i=0; i<legs.length(); i++){
                JSONObject duration = (JSONObject) ((JSONObject)legs.get(i)).get("duration");
                totalTravelTime += duration.getInt("value");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return totalTravelTime;
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
