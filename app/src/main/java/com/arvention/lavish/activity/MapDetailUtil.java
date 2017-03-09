package com.arvention.lavish.activity;

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

}
