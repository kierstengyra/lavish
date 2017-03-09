package com.arvention.lavish.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.arvention.lavish.R;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.PolyUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener{

    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;

    private Polyline directions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create an instance of GoogleAPIClient.
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addApi(LocationServices.API)
                    .build();
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMarkerClickListener(this);

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        }

        LatLng coord1 = new LatLng(14.262753, 121.054529);
        LatLng coord2 = new LatLng(14.267413, 121.052429);
        LatLng coord3 = new LatLng(14.253030, 121.055917);
        addMapMarker(coord1);
        addMapMarker(coord2);
        addMapMarker(coord3);

        mMap.moveCamera(CameraUpdateFactory.zoomBy(15));
    }

    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        drawDirections(marker);
        Log.d("MainActivity","Marker Clicked");
        return false;
    }

    private void addMapMarker(LatLng coordinates) {
        mMap.addMarker(new MarkerOptions().position(coordinates));
        //TODO: add more details for marker (add an identifier)
    }

    private void addMapMarker(LatLng coordinates, String title) {
        mMap.addMarker(new MarkerOptions().position(coordinates));
        //TODO: add more details for marker (add an identifier)
    }

    private void clearMapMarkers() {
        mMap.clear();
    }

    private void drawDirections(Marker marker) {
        clearDirections();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        LatLng dest = marker.getPosition();

        double oriLat =  LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient).getLatitude();
        double oriLong =  LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient).getLongitude();
        LatLng orig = new LatLng(oriLat,oriLong);
        String params = "origin="+oriLat+","+oriLong
                +"&destination="+dest.latitude+","+dest.longitude
                +"&key="+getString(R.string.google_maps_key);
        String url = getString(R.string.dir_http).concat(params);

        new JSONParser().execute(url);
    }

    private void clearDirections(){
        if(directions!=null)
            directions.remove();
    }

    private class JSONParser extends AsyncTask<String, String, Void> {
        InputStream inputStream = null;
        String result = "";
        JSONObject parsedObject;

        @Override
        protected Void doInBackground(String... params) {

            try {
                // Set up HTTP post
                URL url = new URL(params[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                // Read content & Log
                inputStream = new BufferedInputStream(connection.getInputStream());
            } catch (UnsupportedEncodingException e1) {
                Log.e("JSONParser", e1.toString());
            } catch (IllegalStateException e3) {
                Log.e("JSONParser", e3.toString());
                e3.printStackTrace();
            } catch (IOException e4) {
                Log.e("JSONParser", e4.toString());
                e4.printStackTrace();
            }
            // Convert response to string using String Builder
            try {
                BufferedReader bReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"), 8);
                StringBuilder sBuilder = new StringBuilder();

                String line = null;
                while ((line = bReader.readLine()) != null) {
                    sBuilder.append(line + "\n");
                }

                inputStream.close();
                result = sBuilder.toString();

            } catch (Exception e) {
                Log.e("JSONParser", "Error converting result " + e.toString());
            }
            return null;
        }

        protected void onPostExecute(Void v) {
            //parse JSON data
            try {
                parsedObject = new JSONObject(result);
                JSONObject route = (JSONObject) ((JSONArray) parsedObject.get("routes")).get(0);
                String pointsHash = (String) ((JSONObject) route.get("overview_polyline")).get("points");

                List<LatLng> latLngs = PolyUtil.decode(pointsHash);

                directions = mMap.addPolyline(new PolylineOptions().addAll(latLngs));

                Log.d("JSONParser", "Result: " + parsedObject.toString());
                Log.d("JSONParser", "pointsHash: " + pointsHash);
                Log.d("JSONParser", "Processing finished");
            } catch (JSONException e) {
                Log.e("JSONException", "Error: " + e.toString());
            }
        }

    }

}
