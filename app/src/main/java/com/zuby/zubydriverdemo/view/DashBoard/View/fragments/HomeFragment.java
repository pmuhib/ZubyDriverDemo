package com.zuby.zubydriverdemo.view.DashBoard.View.fragments;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
//import com.google.android.gms.location.LocationListener;
import android.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.zuby.zubydriverdemo.R;
import com.zuby.zubydriverdemo.RabbitMq.RPCServer;
import com.zuby.zubydriverdemo.Utils.Utilities;
import com.zuby.zubydriverdemo.view.DashBoard.Presenter.ItemAdapter;
import com.zuby.zubydriverdemo.view.DashBoard.Presenter.routefinder.DirectionFinder;
import com.zuby.zubydriverdemo.view.DashBoard.Presenter.routefinder.DirectionFinderListener;
import com.zuby.zubydriverdemo.view.DashBoard.Presenter.routefinder.Route;
import com.zuby.zubydriverdemo.view.DashBoard.View.DashBoardActivity;
import com.zuby.zubydriverdemo.view.DashBoard.model.Item;
import com.zuby.zubydriverdemo.view.Registration.View.SplashActivity;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static android.content.Context.LOCATION_SERVICE;
import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by citymapper-pc5 on 21/5/18.
 */

public class HomeFragment extends Fragment implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener,ItemAdapter.ItemListener,DirectionFinderListener {
    private ImageView mImageCity;
    private String mTokenid, mDriverid, mFlag, mStatus, mResponse;
    private Bundle mBundle;
    private GoogleMap mGoogleMap;
    private LocationRequest mLocationRequest;
    private GoogleApiClient mGoogleApiClient;
    private Boolean fromOnMapReady;
    private SupportMapFragment supportfragment;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    private LinearLayout mShowing_inactive;
    private FrameLayout mMap_layout;
    private Location mLastLocation;
    private Marker mCurrLocationMarker;
    private LatLng latLng;
    private int i = 0;
    private RPCServer rpcserver = null;
    private LinearLayout mDriver_request;
    private ImageView mDecline_button;
    private TextView mAccept_text,available_text;
    private double lat, lng;
    Location location; // location

    double latitude; // latitude

    double longitude; // longitude
    protected LocationManager locationManager;
    boolean isGPSEnabled = false;
    private SwitchCompat mSwitchCompat;

    // The minimum distance to change Updates in meters
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters

    // The minimum time between updates in milliseconds
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1; // 1 minute
    private NotificationCompat.Builder mNotificationCompat;
    private BottomSheetBehavior mBottomSheetBehavior;
    private ItemAdapter mAdapter;
    android.support.v4.widget.NestedScrollView bottom_sheetadapterheet;
    private ProgressDialog progressDialog;
    private List<Marker> originMarkers = new ArrayList<>();
    private List<Marker> destinationMarkers = new ArrayList<>();
    private List<Polyline> polylinePaths = new ArrayList<>();
    private ImageView mGooglemap;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.homefragment, container, false);

//        Utilities.checkPermissions(getActivity());
//        Utilities.requestPermissions(getActivity());

        mGooglemap = view.findViewById(R.id.googlemap);
        mShowing_inactive = view.findViewById(R.id.showing_inactive);
        mMap_layout = view.findViewById(R.id.map_layout);
        mDriver_request = view.findViewById(R.id.driver_request);
        mDecline_button = view.findViewById(R.id.decline_button);
        mAccept_text = view.findViewById(R.id.accept_text);
        bottom_sheetadapterheet = view.findViewById(R.id.bottom_sheetadapterheet);

        ActionBar actionbar = ((AppCompatActivity)getActivity()).getSupportActionBar();
//        actionbar.setTitle("Pick up point");

        View view1 = actionbar.getCustomView();

        available_text = view1.findViewById(R.id.available_text);
        mSwitchCompat = view1.findViewById(R.id.switchForActionBar);

        mBundle = getArguments();

        if (mBundle != null) {
            mTokenid = mBundle.getString("tokenid");
            mDriverid = mBundle.getString("user_id");
            mFlag = mBundle.getString("flag");
            mStatus = mBundle.getString("status");

            Log.e("Em", ":::::::::flag::::::::" + " " + mFlag + " " + mStatus + " " + mDriverid + " " + mTokenid);
        }

        Log.e("Zy", "lat & lng" + " " + lat + " " + lng);

        mImageCity = view.findViewById(R.id.city);
        mImageCity.setColorFilter(Color.parseColor("#20C1D3"));
        //        new AsyncService().execute();
        View bottomSheet = view.findViewById( R.id.recyclerView);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mAdapter = new ItemAdapter(createItems(), this);
        recyclerView.setAdapter(mAdapter);

        recyclerView.setVisibility(View.INVISIBLE);
//        bottom_sheetadapterheet.setVisibility(View.VISIBLE);


        mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);

//        mBottomSheetBehavior.setHideable(true);//Important to add
//        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        mBottomSheetBehavior.setPeekHeight(200);


        mGooglemap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // This part is to open google map navigation between source and destination

                String label = "I'm Here!";
                Log.e("Em","latitude/longitude in google map"+" "+latitude+" "+longitude);

                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?saddr="+" "+latitude+" "+","+" "+longitude+" "+"&daddr="+" "+"28.38,77.12"));
                startActivity(intent);
            }
        });

        mBottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(View bottomSheet, int newState)
            {
                if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                    mBottomSheetBehavior.setPeekHeight(200);
                }
            }

            @Override
            public void onSlide(View bottomSheet, float slideOffset)
            {
            }
        });



        mDriver_request.setVisibility(View.VISIBLE);
        mDecline_button.setVisibility(View.VISIBLE);
            Animation hide = AnimationUtils.loadAnimation(getActivity(), R.anim.left_to_right);
            mDriver_request.startAnimation(hide);



        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        locationManager = (LocationManager) getActivity()
                .getSystemService(LOCATION_SERVICE);

        isGPSEnabled = locationManager
                .isProviderEnabled(LocationManager.GPS_PROVIDER);

//        if (isNetworkEnabled) {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
//            return TODO;
        }
//        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, (android.location.LocationListener) this);
//            Log.d("Network", "Network");
//            if (locationManager != null) {
//                location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
//                if (location != null) {
//                    latitude = location.getLatitude();
//                    longitude = location.getLongitude();
//
//                    Log.e("Zy","lat/lng"+" "+latitude+" "+longitude);
//                }
//            }

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
        Log.d("GPS Enabled", "GPS Enabled");
        if (locationManager != null) {
            location = locationManager
                    .getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (location != null) {
                latitude = location.getLatitude();
                longitude = location.getLongitude();

                Log.e("Zy","location changed"+" "+latitude +" "+longitude);
            }
        }

        mAccept_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                available_text.setText("DROP POINT");
                mSwitchCompat.setVisibility(View.GONE);

                Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());
                try
                {
                    mDriver_request.setVisibility(View.GONE);
                    mDecline_button.setVisibility(View.GONE);
                    double latitude_new = 25.477585;
                    double longitude_new = 85.709091;
                    Log.e("Zy",":::::::lat/lng:::::::"+" "+latitude_new+" "+longitude_new);
                    List<Address> addressList = geocoder.getFromLocation(latitude_new, longitude_new, 1);
                    Address address = addressList.get(0);
                    String second = addressList.get(0).getSubLocality();


                    List<Address>addresses = geocoder.getFromLocation(latitude,longitude,1);
                    Address address_new = addresses.get(0);

// address_new.getLocality() mei address aa jata h pura

                    Log.e("Em","address_new"+" "+latitude+" "+longitude+" "+address_new.getLocality()+" "+address_new.getSubLocality()+" "+address_new.getLocale()+" "+address_new.getAddressLine(0));
//                    String one = addresses.get(0).getAddressLine(0);

                    String one = "sector 12,noida";
                    String first = "sector 15,noida";

                    // This method is called to pass source and location to draw polyline between source and location

                    sendRequest(address_new.getLocality(),first);
//                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


        if(mFlag.equalsIgnoreCase("active"))
        {

            // yahan pr asynctask execute krna h
//            new AsyncService().execute();
            Log.e("Em",":::::::active::::::"+" "+mFlag);
            mShowing_inactive.setVisibility(View.GONE);
            mMap_layout.setVisibility(View.VISIBLE);
//            supportfragment = (SupportMapFragment)this.getChildFragmentManager().findFragmentById(R.id.map);
//            supportfragment.getMapAsync(this);


        }
        else
        {

            // yahan pr asynctask cancel krna h
            Log.e("Em",":::::::inactive::::::"+" "+mFlag);
//            new AsyncService().execute();
//            mShowing_inactive.setVisibility(View.VISIBLE);
//            mMap_layout.setVisibility(View.GONE);
            mShowing_inactive.setVisibility(View.GONE);
            mMap_layout.setVisibility(View.VISIBLE);
        }

//        mDecline_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mDriver_request.setVisibility(View.GONE);
//                mDecline_button.setVisibility(View.GONE);
//            }
//        });
//
//        mAccept_text.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mDriver_request.setVisibility(View.GONE);
//                mDecline_button.setVisibility(View.GONE);
//            }
//        });



//        if(mStatus.length()>0 && mStatus!=null && mStatus.equalsIgnoreCase("online") )
//        {
//            try {


//                Log.e("ZY","::::::::::::::mResponse::::::::::"+" "+mResponse.toString());

//                if(mResponse.length()>0 && mResponse!=null)
//                {
//                    mResponse

//                }

//            } catch (IOException e) {
//                e.printStackTrace();
//                Log.e("Zy","exception"+" "+e);
//            }
//        }
//        else
//        {
//            Log.e("Em","homefragment");
//        }



        return view;


    }


    public List<Item> createItems() {

        ArrayList<Item> items = new ArrayList<>();
        items.add(new Item(R.mipmap.ic_launcher, "Item 1"));
        items.add(new Item(R.mipmap.ic_launcher, "Item 2"));
        items.add(new Item(R.mipmap.ic_launcher, "Item 3"));
        items.add(new Item(R.mipmap.ic_launcher, "Item 4"));
        items.add(new Item(R.mipmap.ic_launcher, "Item 5"));
        items.add(new Item(R.mipmap.ic_launcher, "Item 6"));
        items.add(new Item(R.mipmap.ic_launcher, "Item 7"));
        items.add(new Item(R.mipmap.ic_launcher, "Item 8"));
        items.add(new Item(R.mipmap.ic_launcher, "Item 9"));
        items.add(new Item(R.mipmap.ic_launcher, "Item 10"));
        items.add(new Item(R.mipmap.ic_launcher, "Item 11"));
        items.add(new Item(R.mipmap.ic_launcher, "Item 12"));
        items.add(new Item(R.mipmap.ic_launcher, "Item 13"));
        items.add(new Item(R.mipmap.ic_launcher, "Item 14"));
        items.add(new Item(R.mipmap.ic_launcher, "Item 15"));


        return items;
    }


    /**
     * Function to get latitude
     */
    public double getLatitude() {
        if (location != null) {
            latitude = location.getLatitude();
        }

        // return latitude
        return latitude;
    }

    /**
     * Function to get longitude
     */
    public double getLongitude() {
        if (location != null) {
            longitude = location.getLongitude();
        }

        // return longitude
        return longitude;
    }


    private void sendRequest(String origin,String destination)
    {
//        String origin = etOrigin.getText().toString();
//        String destination = etDestination.getText().toString();
//        String origin = "sector-12,noida";
//        String destination = "sector-15,noida";
        if (origin.isEmpty()) {
            Toast.makeText(getActivity(), "Please enter origin address!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (destination.isEmpty()) {
            Toast.makeText(getActivity(), "Please enter destination address!", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            new DirectionFinder(this, origin, destination).execute();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void onConnected(Bundle bundle)
    {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(getActivity(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
//            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.e("Em","connection failed!");
    }


    @Override
    public void onPause() {
//        super.onPause();
//        if (mGoogleApiClient != null) {
//            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
//        }
        super.onPause();
        if (mGoogleApiClient != null)
        {
//            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        }
    }

    @Override
    public void onLocationChanged(Location location)
    {
        mLastLocation = location;
        if (mCurrLocationMarker != null)
        {
            mCurrLocationMarker.remove();
        }

//        if(mStatus.equalsIgnoreCase("online")) {

            latLng = new LatLng(location.getLatitude(), location.getLongitude());

            lat = location.getLatitude();
            lng = location.getLongitude();

        Log.e("Zy","online"+" "+lat+" "+lng);

            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(latLng);
            markerOptions.title("Current Position");
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
            mCurrLocationMarker = mGoogleMap.addMarker(markerOptions);
            mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16.0f));
//        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    protected synchronized void buildGoogleApiClient()
    {
        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        mGoogleMap=googleMap;
//        map=googleMap;

        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
//        map=mGoogleMap;
        //Initialize Google Play Services
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            if (ContextCompat.checkSelfPermission(getActivity(),
                    android.Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED)
            {
                //Location Permission already granted
                buildGoogleApiClient();
                mGoogleMap.setMyLocationEnabled(true);
            }
            else
            {
                //Request Location Permission
                checkLocationPermission();
            }
        }
        else {
            buildGoogleApiClient();
            mGoogleMap.setMyLocationEnabled(true);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(getActivity(),
                            android.Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        if (mGoogleApiClient == null) {
                            buildGoogleApiClient();
                        }
                        mGoogleMap.setMyLocationEnabled(true);
                    }

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(getActivity(), "permission denied", Toast.LENGTH_LONG).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }


    private void checkLocationPermission()
    {
        if (ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    android.Manifest.permission.ACCESS_FINE_LOCATION))
            {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                new AlertDialog.Builder(getActivity())
                        .setTitle("Location Permission Needed")
                        .setMessage("This app needs the Location permission, please accept to use location functionality")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(getActivity(),
                                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION );
                            }
                        })
                        .create()
                        .show();


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION );
            }
        }
    }


    public void showNotification()
    {
        NotificationCompat.Builder mBuilder =   new NotificationCompat.Builder(getActivity())
                .setSmallIcon(R.mipmap.ic_launcher) // notification icon
                .setContentTitle("Notification!") // title for notification
                .setContentText("Hello word") // message for notification
                .setAutoCancel(true); // clear notification after click
//        Intent intent = new Intent(this, getActivity());
//        Intent intent = new Intent(this, DashBoardActivity.class);
//        PendingIntent pi = PendingIntent.getActivity(getActivity(),0,intent,Intent.FLAG_ACTIVITY_NEW_TASK);
//        mBuilder.setContentIntent(pi);
        NotificationManager mNotificationManager =
                (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(0, mBuilder.build());
//        RemoteViews contentView = new RemoteViews(getPackageName(true), R.layout.custom_push);
//        contentView.setImageViewResource(R.id.image, R.mipmap.ic_launcher);
//        contentView.setTextViewText(R.id.title, "Custom notification");
//        contentView.setTextViewText(R.id.text, "This is a custom layout");
//
//        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getActivity())
//                .setSmallIcon(R.mipmap.ic_launcher)
//                .setContent(contentView);
//
//        Notification notification = mBuilder.build();
//        notification.flags |= Notification.FLAG_AUTO_CANCEL;
//        notification.defaults |= Notification.DEFAULT_SOUND;
//        notification.defaults |= Notification.DEFAULT_VIBRATE;
//                NotificationManager mNotificationManager =
//                (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
////        mNotificationManager.notify(0, mBuilder.build());
//        mNotificationManager.notify(1, notification);
    }



    public void CustomNotification() {
        Intent notificationIntent = null;
//        RemoteViews remoteViews =
//                new RemoteViews(getPackageName(true),
//                        R.layout.notification_for_lolipop);
//            remoteViews.setB
//        try {
//            mGoalName = mJsonObj.getString("type");
//            mMessage = mJsonObj.getString("title");
        if ("s".equalsIgnoreCase("s")) {
            notificationIntent = new Intent(getActivity(), SplashActivity.class);
        } else {
            notificationIntent = new Intent(getActivity(), DashBoardActivity.class);
        }
//            notificationIntent.putExtra("type", mJsonObj.getString("type"));
//            notificationIntent.putExtra("title", mJsonObj.getString("title"));
//            notificationIntent.putExtra("id", mJsonObj.getString("id"));
//            notificationIntent.putExtra("parent", mJsonObj.getString("parent"));
//        } catch (Exception e) {
//            Log.e("Em", e.toString());
//        }

        final PendingIntent pIntent =
                PendingIntent.getActivity(
                        getActivity(),
                        0,
                        notificationIntent,
                        PendingIntent.FLAG_IMMUTABLE);
        mNotificationCompat
                = new NotificationCompat.Builder(getActivity().getApplicationContext());
        mNotificationCompat.setSmallIcon(R.mipmap.ic_launcher);
        mNotificationCompat.setContentText("Hello word");
        mNotificationCompat.setTicker("hello");
        mNotificationCompat.setAutoCancel(true);
        mNotificationCompat.setContentIntent(pIntent);
        mNotificationCompat.setPriority(Notification.PRIORITY_LOW);
//        mNotificationCompat.setContent(remoteViews);
//        remoteViews.setImageViewResource(R.id.icon, R.mipmap.ic_launcher);
//        remoteViews.setTextViewText(R.id.goal_name, mGoalName);
//        remoteViews.setTextViewText(R.id.text, "hello");
        NotificationManager notificationmanager = (NotificationManager)getActivity().getSystemService(NOTIFICATION_SERVICE);
        notificationmanager.notify(0, mNotificationCompat.build());
        i++;
    }

    @Override
    public void onItemClick(Item item) {

    }

    @Override
    public void onDirectionFinderStart() {
        progressDialog = ProgressDialog.show(getActivity(), "Please wait.",
                "Finding direction..!", true);

        if (originMarkers != null) {
            for (Marker marker : originMarkers) {
                marker.remove();
            }
        }

        if (destinationMarkers != null) {
            for (Marker marker : destinationMarkers) {
                marker.remove();
            }
        }

        if (polylinePaths != null) {
            for (Polyline polyline : polylinePaths) {
                polyline.remove();
            }
        }
    }

    @Override
    public void onDirectionFinderSuccess(List<Route> route)
    {
        progressDialog.dismiss();
        polylinePaths = new ArrayList<>();
        originMarkers = new ArrayList<>();
        destinationMarkers = new ArrayList<>();

        for (Route routes : route)
        {
            mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(routes.startLocation, 16));
//            ((TextView) findViewById(R.id.tvDuration)).setText(routes.duration.text);
//            ((TextView) findViewById(R.id.tvDistance)).setText(routes.distance.text);

            Log.e("Em",":::::distance:::::"+" "+routes.duration.text);



            originMarkers.add(mGoogleMap.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.start))
                    .title(routes.startAddress)
                    .position(routes.startLocation)));

            destinationMarkers.add(mGoogleMap.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.stop))
                    .title(routes.endAddress)
                    .position(routes.endLocation)));

            PolylineOptions polylineOptions = new PolylineOptions().
                    geodesic(true).
                    color(Color.BLUE).
                    width(10);

            for (int i = 0; i < routes.points.size(); i++)
                polylineOptions.add(routes.points.get(i));

            polylinePaths.add(mGoogleMap.addPolyline(polylineOptions));
        }

    }


    public class AsyncService extends AsyncTask<String,String,String>
    {

        @Override
        protected String doInBackground(String... strings)
        {
            try {
                rpcserver = new RPCServer();
                mResponse=rpcserver.startConsuming();
                Log.e("Em","mresponse"+" "+mResponse);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return mResponse;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Log.e("Zy","result"+" "+s);
            showNotification();
            CustomNotification();
            mDriver_request.setVisibility(View.VISIBLE);
            mDecline_button.setVisibility(View.VISIBLE);
//            Animation hide = AnimationUtils.loadAnimation(getActivity(), R.anim.bottom_to_top);
//            mDriver_request.startAnimation(hide);

        }
    }



}
