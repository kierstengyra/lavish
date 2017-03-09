package com.arvention.lavish.activity;

import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.arvention.lavish.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
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

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback{
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        JSONParser parser = new JSONParser();
        String url = "https://maps.googleapis.com/maps/api/directions/json?origin=14.261469,121.042557&destination=14.262822,121.044076&key=AIzaSyCYlOr4jqWPkgaEgJz00bSTcLzYX5OEJFo";
        parser.execute(url);
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

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        }

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,15));
    }

    private class JSONParser extends AsyncTask< String, String, Void> {
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
                JSONObject route = (JSONObject)((JSONArray) parsedObject.get("routes")).get(0);
                String pointsHash = (String)((JSONObject)route.get("overview_polyline")).get("points");

                List<LatLng> latLngs = PolyUtil.decode(pointsHash);
                mMap.addPolyline(new PolylineOptions().addAll(latLngs));

                Log.d("JSONParser","Result: "+parsedObject.toString());
                Log.d("JSONParser","pointsHash: "+pointsHash);
                Log.d("JSONParser","Processing finished");
            } catch (JSONException e) {
                Log.e("JSONException", "Error: " + e.toString());
            }
        }

    }
}
