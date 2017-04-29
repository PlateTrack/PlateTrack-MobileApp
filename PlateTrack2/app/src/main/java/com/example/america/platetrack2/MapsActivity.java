package com.example.america.platetrack2;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import org.w3c.dom.Document;

import java.util.ArrayList;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    public static ArrayList<PlateCapture> plateCaptures;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

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

        if (MapsActivity.plateCaptures != null && MapsActivity.plateCaptures.size() > 0) {

            mMap = googleMap;
            mMap.setMinZoomPreference(14.0f);


            // MapsActivity.plateCaptures to load LatLngs and create routes
            // Example

            PlateCapture previous = null;
            for (PlateCapture pc : MapsActivity.plateCaptures)
            {
                // This means its the beginning of the routes
                if (previous == null){
                    previous = pc;
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(pc.getLatitude(), pc.getLongitude())));
                }
                // As an example these will all be blue. Eventually we will need to differentiate using the date and time properties and use differing colors.
                else {
                    LatLng start = new LatLng(previous.getLatitude(), previous.getLongitude());
                    LatLng end = new LatLng(pc.getLongitude(), pc.getLatitude());
                    route(start, end, GMapV2Direction.MODE_DRIVING, Color.BLUE);
                }
            }
        }
        else {
            return;
        }


//        // mMap.setMyLocationEnabled(true);
//        // Add a marker in Sydney and move the camera
//        LatLng springfield = new LatLng(37.2090, -93.2923);
//        LatLng springfield2 = new LatLng(37.207096, -93.292449);
//        LatLng springfield3 = new LatLng(37.206301, -93.292361);
//        LatLng springfield4 = new LatLng(37.000, -93.200);
//
////        mMap.addMarker(new MarkerOptions().position(springfield).title("Marker in Springfield"));
////
////        mMap.addMarker(new MarkerOptions().position(springfield2).title("Marker in Springfield2"));
//
//
//
//
//        route(springfield2, springfield, GMapV2Direction.MODE_DRIVING, Color.BLUE);
//        route(springfield, springfield2, GMapV2Direction.MODE_DRIVING, Color.RED);
//
//        route(springfield3, springfield4, GMapV2Direction.MODE_WALKING, Color.GREEN);

    }

    protected void route(LatLng sourcePosition, LatLng destPosition, String mode, final int color) {
        final Handler handler = new Handler() {
            public void handleMessage(Message msg) {
                try {
                    Document doc = (Document) msg.obj;
                    GMapV2Direction md = new GMapV2Direction();
                    ArrayList<LatLng> directionPoint = md.getDirection(doc);
                    PolylineOptions rectLine = new PolylineOptions().width(3).color(color);

                    for (int i = 0; i < directionPoint.size(); i++) {
                        rectLine.add(directionPoint.get(i));
                    }
                    Polyline polylin = mMap.addPolyline(rectLine);
                    md.getDurationText(doc);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            ;
        };

        new GMapV2DirectionAsyncTask(handler, sourcePosition, destPosition, mode, color).execute();

    }
}