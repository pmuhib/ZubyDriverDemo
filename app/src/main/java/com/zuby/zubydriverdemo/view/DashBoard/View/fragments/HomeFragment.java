package com.zuby.zubydriverdemo.view.DashBoard.View.fragments;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
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
import com.zuby.zubydriverdemo.R;
import com.zuby.zubydriverdemo.Utils.Utilities;

/**
 * Created by citymapper-pc5 on 21/5/18.
 */

public class HomeFragment extends Fragment implements OnMapReadyCallback,GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener,LocationListener
{
    private ImageView mImageCity;
    private String mTokenid,mDriverid,mFlag,mStatus;
    private Bundle mBundle;
    private GoogleMap mGoogleMap;
    private LocationRequest mLocationRequest;
    private GoogleApiClient mGoogleApiClient;
    private Boolean fromOnMapReady;
    private SupportMapFragment supportfragment;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    private LinearLayout mShowing_inactive,mMap_layout;
    private Location mLastLocation;
    private Marker mCurrLocationMarker;
    private LatLng latLng;


    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.homefragment,container,false);

        Utilities.checkPermissions(getActivity());
        Utilities.requestPermissions(getActivity());

        mShowing_inactive = view.findViewById(R.id.showing_inactive);
        mMap_layout = view.findViewById(R.id.map_layout);

        mBundle = getArguments();

        if(mBundle!=null)
        {
            mTokenid = mBundle.getString("tokenid");
            mDriverid = mBundle.getString("user_id");
            mFlag = mBundle.getString("flag");
            mStatus= mBundle.getString("status");

            Log.e("Em",":::::::::flag::::::::"+" "+mFlag+" "+mStatus+" "+mDriverid+" "+mTokenid);
        }

        mImageCity=view.findViewById(R.id.city);
        mImageCity.setColorFilter(Color.parseColor("#20C1D3"));

        if(mFlag.equalsIgnoreCase("active"))
        {
            Log.e("Em",":::::::active::::::"+" "+mFlag);
            mShowing_inactive.setVisibility(View.GONE);
            mMap_layout.setVisibility(View.VISIBLE);
            supportfragment = (SupportMapFragment)this.getChildFragmentManager().findFragmentById(R.id.map);
            supportfragment.getMapAsync(this);

        }
        else
        {
            Log.e("Em",":::::::inactive::::::"+" "+mFlag);
            mShowing_inactive.setVisibility(View.VISIBLE);
            mMap_layout.setVisibility(View.GONE);
        }



        return view;


    }


    @Override
    public void onConnected(Bundle bundle) {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
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
    public void onLocationChanged(Location location)
    {
        mLastLocation = location;
        if (mCurrLocationMarker != null)
        {
            mCurrLocationMarker.remove();
        }

//        if(mStatus.equalsIgnoreCase("online")) {
            Log.e("Zy","online");
            latLng = new LatLng(location.getLatitude(), location.getLongitude());
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(latLng);
            markerOptions.title("Current Position");
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
            mCurrLocationMarker = mGoogleMap.addMarker(markerOptions);
            mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16.0f));
//        }
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
        mGoogleMap = googleMap;

        fromOnMapReady = true;

        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

//        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
//        {
//            if (ContextCompat.checkSelfPermission(getActivity(),
//                    android.Manifest.permission.ACCESS_FINE_LOCATION)
//                    == PackageManager.PERMISSION_GRANTED)
//            {
//                //Location Permission already granted
//                try {
//                    buildGoogleApiClient();
//                    mGoogleMap.setMyLocationEnabled(true);
//
//                    View locationButton = ((View) supportfragment.getView().findViewById(Integer.parseInt("1")).getParent()).findViewById(Integer.parseInt("2"));
//                    RelativeLayout.LayoutParams rlp = (RelativeLayout.LayoutParams) locationButton.getLayoutParams();
//                    rlp.addRule(RelativeLayout.ALIGN_PARENT_TOP, 0);
//                    rlp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
//                    rlp.setMargins(0, 0, 30, 30);
//
//
//
//
//                }
//                catch (Exception e)
//                {
//                    Log.e("Em","network exception check 2");
//                }
//
//            }
//            else
//            {
//                //Request Location Permission
//                checkLocationPermission();
//            }
//        }
//        else
//        {
//            try
//            {
//                buildGoogleApiClient();
//                mGoogleMap.setMyLocationEnabled(true);
//                View locationButton = ((View) supportfragment.getView().findViewById(Integer.parseInt("1")).getParent()).findViewById(Integer.parseInt("2"));
//                RelativeLayout.LayoutParams rlp = (RelativeLayout.LayoutParams) locationButton.getLayoutParams();
//                rlp.addRule(RelativeLayout.ALIGN_PARENT_TOP, 0);
//                rlp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
//                rlp.setMargins(0, 0, 30, 30);
//
//            }
//            catch (Exception e)
//            {
//                Log.e("Em","Network exception check 3"+" "+e);
//            }
//
//        }

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(getActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                //Location Permission already granted
                buildGoogleApiClient();
                mGoogleMap.setMyLocationEnabled(true);
            } else {
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
                            Manifest.permission.ACCESS_FINE_LOCATION)
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
                    android.Manifest.permission.ACCESS_FINE_LOCATION)) {

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
                                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
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
                Log.e("EM","ON PERMISSION requested");


            }
        }
    }
}
